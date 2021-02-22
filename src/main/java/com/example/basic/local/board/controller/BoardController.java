package com.example.basic.local.board.controller;

import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.model.ListResponse;
import com.example.basic.global.common.response.model.SingleResponse;
import com.example.basic.global.common.response.service.ResponseService;
import com.example.basic.local.board.entity.Board;
import com.example.basic.local.board.entity.Post;
import com.example.basic.local.board.model.ParamsPost;
import com.example.basic.local.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "게시판", description = "게시판 api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/test/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @Operation(summary = "게시판 생성", description = "신규 게시판을 생성한다.")
    @PostMapping(value = "/{boardName}")
    public SingleResponse<Board> createBoard(@PathVariable String boardName) {
        return responseService.getSingleResult(boardService.insertBoard(boardName));
    }

    @Operation(summary = "게시판 정보 조회", description = "게시판 정보를 조회한다.")
    @GetMapping(value = "/{boardName}")
    public SingleResponse<Board> boardInfo(@PathVariable String boardName) {
        return responseService.getSingleResult(boardService.findBoard(boardName));
    }

    @Operation(summary = "게시글 리스트", description = "게시글 리스트를 조회한다.")
    @GetMapping(value = "/{boardName}/posts")
    public ListResponse<Post> posts(@PathVariable String boardName) {
        return responseService.getListResult(boardService.findPosts(boardName));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @Operation(summary = "게시글 작성", description = "게시글을 작성한다.")
    @PostMapping(value = "/{boardName}/post")
    public SingleResponse<Post> post(@PathVariable String boardName, @Valid @ModelAttribute ParamsPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.writePost(uid, boardName, post));
    }

    @Operation(summary = "게시글 상세", description = "게시글 상세정보를 조회한다.")
    @GetMapping(value = "/post/{postId}")
    public SingleResponse<Post> post(@PathVariable long postId) {
        return responseService.getSingleResult(boardService.getPost(postId));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @Operation(summary = "게시글 수정", description = "게시판의 글을 수정한다.")
    @PutMapping(value = "/post/{postId}")
    public SingleResponse<Post> post(@PathVariable long postId, @Valid @ModelAttribute ParamsPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.updatePost(postId, uid, post));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제한다.")
    @DeleteMapping(value = "/post/{postId}")
    public CommonResponse deletePost(@PathVariable long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        boardService.deletePost(postId, uid);
        return responseService.getSuccessResult();
    }
}
