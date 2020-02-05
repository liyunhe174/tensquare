package com.tensquare.search.pjo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/23 18:03
 * @Description:
 */
@Document(indexName = "tensquare_article" , type = "article")
@Data
public class Article implements Serializable {

    @Id
    private String id;

    @Field(index = true,analyzer = "ik_max_word" , searchAnalyzer = "ik_max_word")
    private String title;

    @Field(index = true,analyzer = "ik_max_word" , searchAnalyzer = "ik_max_word")
    private String content;

    private String state;

}
