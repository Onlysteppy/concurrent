package com.cry.chapter03.flight;

import com.cry.chapter03.ThreadJoin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FlightQueryExample {
    //1、合作的各大航空公司
    private static List<String> flightCompany = Arrays.asList(
            "CAS", "CEA", "HNA"
    );

    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("==========result==============");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        ArrayList<String> result = new ArrayList<>();
        //2、创建查询航班信息的线程列表
        List<FlightQueryTask> tasks = flightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(toList());
        //3、分别启动这几个线程
        tasks.forEach(Thread::start);
        //4、分别调用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t -> {

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //5、在此之前，当前线程回阻塞住，获取每一个查询线程的结果，并且加入到result中。
        tasks.stream().map(FlightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FlightQueryTask createSearchTask(String flight, String original, String dest) {
        return new FlightQueryTask(flight, original, dest);
    }
}
