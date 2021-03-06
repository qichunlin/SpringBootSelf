package com.legend.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * 文档数据新增
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2021/4/13
 */
public class EsTest_Client_Doc_Insert {

    public static void main(String[] args) throws Exception {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //插入数据
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1011");

        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setTel("10010");
        user.setSex("男");

        //向ES插入数据，必须将数据转化为JSON格式
        ObjectMapper objectMapper = new ObjectMapper();
        String userJsonStr = objectMapper.writeValueAsString(user);
        indexRequest.source(userJsonStr, XContentType.JSON);

        IndexResponse response = esClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.getResult());

        //关闭客户端
        esClient.close();
    }
}
