package com.example.basic.local.board.service;

import com.example.basic.global.advice.exception.CustomNotOwnerException;
import com.example.basic.global.advice.exception.CustomResourceNotExistException;
import com.example.basic.global.advice.exception.CustomUserNotFoundException;
import com.example.basic.global.annotation.ForbiddenWordCheck;
import com.example.basic.global.common.CacheKey;
import com.example.basic.local.board.entity.Board;
import com.example.basic.local.board.entity.Post;
import com.example.basic.local.board.model.ParamsPost;
import com.example.basic.local.board.repository.BoardJpaRepository;
import com.example.basic.local.board.repository.PostJpaRepository;
import com.example.basic.local.user.entity.User;
import com.example.basic.local.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public Board insertBoard(String boardName) {
        return boardJpaRepository.save(Board.builder().name(boardName).build());
    }

    /*
     * 게시판 이름으로 게시판 조회, 없으면 ResourceNotExistException
     * @Cacheable : 캐시가 존재하면 메서드 실행하지 않고, 캐시된 값이 반환
     * 캐시가 존재하지 않으면 메서드 실행되고 리턴되는 데이터가 캐시에 저장
     */
    @Cacheable(value = CacheKey.BOARD, key = "#boardName", unless = "#result == null")
    public Board findBoard(String boardName){
        return Optional.ofNullable(boardJpaRepository.findByName(boardName)).orElseThrow(CustomResourceNotExistException::new);
    }

    /*
     * 게시판 이름으로 게시글 리스트 조회
     * @Caching : 여러개의 캐시 annotation을 싱행되어야 할 때 사용
     */
    @Cacheable(value = CacheKey.POSTS, key = "#boardName", unless = "#result == null")
    public List<Post> findPosts(String boardName){
        return postJpaRepository.findByBoardOrderByPostIdDesc(findBoard(boardName));
    }

    /*
     * 게시글 ID 로 게시글 단건 조회, 없으면 ResourceNotExistException
     */
    @Cacheable(value = CacheKey.POST, key = "#postId", unless = "#result == null")
    public Post getPost(long postId){
        return postJpaRepository.findById(postId).orElseThrow(CustomResourceNotExistException::new);
    }

    /*
     * 게시글 등록, 게시글의 회원 ID가 조회되지 않으면 UserNotFoundException
     * @CacheEvict : 캐시 삭제
     */
    @CacheEvict(value = CacheKey.POSTS, key = "#boardName")
    @ForbiddenWordCheck
    public Post writePost(String uid, String boardName, ParamsPost paramPost){
        Board board = findBoard(boardName);
        //금칙어 체크
//        checkForbiddenWord(paramPost.getContent());
        Post post = new Post(userJpaRepository.findByUid(uid).orElseThrow(CustomUserNotFoundException::new), board, paramPost.getAuthor(), paramPost.getTitle(), paramPost.getContent());
        return postJpaRepository.save(post);
    }

    /*
     * 게시글 수정, 게시글 등록자와 로그인 회원 정보가 다르면 NotOwnerException
     * @CachePut : 캐시에 데이터를 넣거나 수정시 사용,
     * 메서드의 리턴값이 캐시에 없으면 저장, 있을경우 갱신
     */
    //@CachePut(value = CacheKey.POST, key = "#postId") 갱신된 정보만 캐시할경우에만 사용!
    @ForbiddenWordCheck
    public Post updatePost(long postId, String uid, ParamsPost paramPost){
        Post post = getPost(postId);
        User user = post.getUser();
        if(!uid.equals(user.getUid())){
            throw new CustomNotOwnerException();
        }
//        checkForbiddenWord(paramPost.getContent());
        //영속성 컨텍스트의 변경감지(dirty checking) 기능에 의해 조회한 post 내용을 변경만 해도 update 쿼리가 실행
        post.setUpdate(paramPost.getAuthor(), paramPost.getTitle(), paramPost.getContent());
        return post;
    }

    /*
     * 게시글 삭제, 게시글 등록자와 로그인 회원 정보가 다르면 NotOwnerException
     */
    public boolean deletePost(long postId, String uid){
        Post post = getPost(postId);
        User user = post.getUser();
        if(!uid.equals(user.getUid())){
            throw new CustomNotOwnerException();
        }
        postJpaRepository.delete(post);
        return true;
    }

//    public void checkForbiddenWord(String word){
//        List<String> forbiddenWords = Arrays.asList("개새끼","쌍년","씨발");
//        Optional<String> forbiddenWord = forbiddenWords.stream().filter(word::contains).findFirst();
//        if(forbiddenWord.isPresent()){
//            throw new CustomForbiddenWordException(forbiddenWord.get());
//        }
//    }

    @CacheEvict(cacheNames = {"User"}, allEntries = true)
    public void clearCache(){}
}
