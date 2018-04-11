package com.reactive;

import rx.Observable;
import rx.Subscriber;

public class ReactiveSampleMain {
	static String result = "";
	private static String evenList = "";
	private static String oddList = "";
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
		//i
		observable.subscribe(subscriber);
		
		//ii
		String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
		Observable<String> observable1 = Observable.from(letters);
		observable1.subscribe(
		  i -> result += i,  //OnNext
		  Throwable::printStackTrace, //OnError
		  () -> System.out.println(result +"_Completed") //OnCompleted
		);
		
		//iii
		result = "";
		Observable.from(letters)
				.map(i->i.toUpperCase())
				.subscribe(
						i->result+=i,
						Throwable::printStackTrace,
						()->System.out.println(result)
						);
		
		//iv
		result = "";
		Observable.just("Hi ", "Hello").subscribe(s->result+=s,Throwable::printStackTrace,
				()->System.out.println(result));
		
		//v
		result = "";
		Observable.from(letters)
		.scan(new StringBuilder(),StringBuilder::append)
		.subscribe(i->result+=i,Throwable::printStackTrace,()->System.out.println(result));
		
		//vi
		Integer[] numbers = {1,2,3,4,5,6,7,8,9};
		Observable.from(numbers).groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
		.subscribe(group -> group.subscribe(n -> {
			if (group.getKey().equals("EVEN")) {
				evenList += n;
			}else {
				oddList += n;
			}
		}),Throwable::printStackTrace,
			() ->System.out.println("EvenList " +evenList+ " OddList "+oddList));
		
		//vii
		evenList = "";
		Observable.from(numbers).filter(n->(n%2)==0)
		.subscribe(i->evenList+=i,Throwable::printStackTrace,()->System.out.println(evenList));
		
		//viii
		result = "";
		Observable.empty().defaultIfEmpty("I am Empty").subscribe(i->result+=i,
				Throwable::printStackTrace,()->System.out.println(result));
		
		//ix
		result = "";
		Observable.from(letters).defaultIfEmpty("I am Empty").first().subscribe(i->result+=i,
				Throwable::printStackTrace,()->System.out.println(result));
		
		//x
		result = "";
		Observable.from(numbers).takeWhile(i->i<5)
		.subscribe(i->result+=i,Throwable::printStackTrace,()->System.out.println(result));
		
	}

}
