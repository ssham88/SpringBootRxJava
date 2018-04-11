package com.reactive;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class ReactiveSampleMain {
	public static void main(String args[]) {
		//1 
		//Observable<String> observable = Observable.just("Hello");
		//2
		/*List<String> stringArray = new ArrayList<>();
		stringArray.add("1");
		stringArray.add("2");
		stringArray.add("3");
		stringArray.add("4");
		Observable<String> observable = Observable.from(stringArray);*/
		//3
		/*Observable<Integer> observable = Observable.just("5").map(new Func1<String ,String>() {

			@Override
			public String call(String t) {
				return "Hi"+t;
			}
			//OUTPUT : Hi5
		}).map(new Func1<String ,String>(){

			@Override
			public String call(String t) {
				// TODO Auto-generated method stub
				return t + " dude!!"; 
			}
			//OUTPUT : Hi5 dude!!
		}).map(new Func1<String,Integer>(){

			@Override
			public Integer call(String t) {
			 //return Integer.parseInt(t); OUTPUT : ERROR!!!
				return 10;
			}
		});*/
		Observable<Integer> observable = Observable.just("5")
				.map(t->"Hi"+t)
				.map(t->t +" dude!!")
				.map(t->10);
		//1
		/*Subscriber<String> subscriber = new Subscriber<String>() {

			@Override
			public void onCompleted() {
				System.out.println("I am done!!!");				
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("ERROR!!!");		
			}

			@Override
			public void onNext(String t) {
				System.out.println("Next... "+t);	
			}
		};*/
		//2
		Subscriber<Integer> subscriber = new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("I am done!!!");				
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("ERROR!!!");		
			}

			@Override
			public void onNext(Integer t) {
				System.out.println("Next... "+t);	
			}
		};	
		
		observable.subscribe(subscriber);
	}
}
