package com.legend.elasticsearch;

import com.legend.elasticsearch.entity.Item;
import com.legend.elasticsearch.respository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 采用类的字节码信息创建索引并映射
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchAppMain.class)
public class IndexTest {

    private static final Logger log = LoggerFactory.getLogger(IndexTest.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引库和类型(表结构映射)
     */
    @Test
    public void testCreate() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }


    @Autowired
    private ItemRepository itemRepository;

    /**
     * 新增文档(表数据)
     */
    @Test
    public void index() {
        Item item = new Item(10L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        boolean deleteResult = elasticsearchTemplate.deleteIndex("deletetest");
        System.out.println("删除索引库是否成功..." + deleteResult);
    }


    /**
     * 批量新增
     */
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(20L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(30L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }


    /**
     * 查询功能
     */
    @Test
    public void testQuery() {
        Optional<Item> optional = this.itemRepository.findById(10L);
        System.out.println(optional.get());
    }

    /**
     * 查询全部，并按照价格降序排序
     */
    @Test
    public void testFind() {
        // 查询全部，并按照价格降序排序
        //Iterable<Item> items = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        Iterable<Item> items = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price").descending());
        items.forEach(System.out::println);
        //items.forEach(item-> System.out.println(item));
    }


    /**
     * 使用自定义方法查询数据
     */
    @Test
    public void testFindByTittle() {
        // 使用自定义方法查询数据
        Iterable<Item> items = this.itemRepository.findByTitle("手机");
        items.forEach(System.out::println);
        //items.forEach(item-> System.out.println(item));
    }

    /**
     * 使用自定义方法查询数据2
     */
    @Test
    public void testFindByPriceBetween() {
        // 使用自定义方法查询数据
        Iterable<Item> items = this.itemRepository.findByPriceBetween(3699D, 5999D);
        items.forEach(System.out::println);
        //items.forEach(item-> System.out.println(item));
    }


    /**
     * 高级查询
     */
    @Test
    public void testSearch() {
        // 通过查询构建器工具构建查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手机");

        //执行查询获取数据结果集
        Iterable<Item> items = this.itemRepository.search(matchQueryBuilder);
        items.forEach(System.out::println);
        //items.forEach(item-> System.out.println(item));
    }


    /**
     * 自定义参数查询
     */
    @Test
    public void testNative() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加基本的查询条件
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));

        //执行查询获取数据结果集
        Page<Item> itemPage = this.itemRepository.search(nativeSearchQueryBuilder.build());
        log.info("总页数：{}", itemPage.getTotalPages());
        log.info("总条数：{}", itemPage.getTotalElements());

        //获取集合内容
        List<Item> itemList = itemPage.getContent();
        itemList.forEach(System.out::println);
    }

    /**
     * 测试分页
     */
    @Test
    public void testPage() {
        // 构建自定义查询构建器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加基本的查询条件
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        //添加分页条件,页码是从0开始
        nativeSearchQueryBuilder.withPageable(PageRequest.of(1, 2));

        //执行查询获取数据结果集
        Page<Item> itemPage = this.itemRepository.search(nativeSearchQueryBuilder.build());
        log.info("总页数：{}", itemPage.getTotalPages());
        log.info("总条数：{}", itemPage.getTotalElements());

        //获取集合内容
        List<Item> itemList = itemPage.getContent();
        itemList.forEach(System.out::println);
    }


}