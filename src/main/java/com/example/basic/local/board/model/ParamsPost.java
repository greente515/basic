package com.example.basic.local.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 게시물 등록/수정 시 입력 파라미터를 받기 위한 DTO
 */

@Schema(description = "게시물 등록/수정 시 입력 파라미터를 받기 위한 DTO")
@Getter @Setter
@NoArgsConstructor
public class ParamsPost {

//    @ApiModelProperty(value = "작성자", required = true)
    @Schema(description = "작성자", required = true)
    @NotEmpty
    @Size(min = 2, max = 50)
    private String author;

//    @ApiModelProperty(value = "제목", required = true)
    @Schema(description = "제목", required = true)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String title;

//    @ApiModelProperty(value = "내용", required = true)
    @Schema(description = "내용", required = true)
    @NotEmpty
    @Size(min = 2, max = 500)
    private String content;
}
