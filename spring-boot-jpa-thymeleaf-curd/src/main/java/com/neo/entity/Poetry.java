package com.neo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author xiefei
 * @create 2018-11-06 2:36 PM
 * @desc 诗歌ES对象
 **/
@Data
@Document(indexName = "poetry", type = "poetry", shards = 1, replicas = 0, refreshInterval = "-1")
public class Poetry implements Serializable {

    private static final long serialVersionUID = 7025562383621586649L;

    @Id
    private String id = String.valueOf(UUID.randomUUID());

    private String title;

    private String content;

    private String author;

    private String dynasty;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime = new Date();

    private String createdBy;
}
