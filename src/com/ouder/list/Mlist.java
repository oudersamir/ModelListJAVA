package com.ouder.list;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h1> Class Mlist </h1>
 * Module list class is an example of personalized linked list 
 * all methods created by functional programming concepts
 * Classe Module list
 * @author Ouder Samir
 * @version 1.0
 */
public class Mlist {
/**
 * Interface list 	
 * @author Ouder Samir
 * @param <T>
 */
public static interface list<T> {
	/**
	 * @return T head element with generic type
	 */
	T head();
	/**
	 * @return list<T> without head
	 */
	list<T> tail();
	/**
	 * Returns the number of elements in this list.
	 * @return Integer
	 */
	default public int size() {return 0;}
	/**
	 * Returns true if this list contains no elements.
	 * @return true is empty , false is not empty
	 */
	default public boolean isEmpty() {return true;}
	/**
	 * Returns true if this list contains the specified element.
	 * @param specified element with generic type T 
	 * @return boolean
	 */
	default public boolean contains(T e) {return false;}
	/**
	 * the fold left functional gives us a way to visite  each element of a list right to left 
	 * @param e neutral element 
	 * @param f instance of BinaryOperator<T> interface to implements apply function   with two params
	 * @return T
	 */
	default public T FoldL(T e ,BinaryOperator<T> f){return e;}
	/**
	 * the fold right functional gives us a way to visite  each element of a list left to right
	 * @param e neutral element 
	 * @param f instance of BinaryOperator<T> interface to implements apply function   with two params
	 * @return T
	 */
	default public T FoldR(T e, BinaryOperator<T> f) {return e;}
	/**
	 * the fold left functional gives us a way to visite  each element of a list right to left 
	 * @param e neutral element 
	 * @param f instance of BiFunction<T,U,U> interface to implements apply function with two params
	 * @return U 
	 */
	default public <U> U FoldL(U e,BiFunction<T,U,U> f) {return e;}
	/**
	 * the fold right functional gives us a way to visite  each element of a list left to right 
	 * @param e neutral element 
	 * @param f instance of BiFunction<T,U,U> interface to implements apply function with two params
	 * @return U 
	 */
	default public <U> U FoldR(U e,BiFunction<T,U,U> f) {return e;}
	/**
	 * 
	 * @param p
	 * @return
	 */
	default public list<T> filter(Predicate<T> p){ return emptyList();}
	default public list<T> map(Function<T,T> f){ return emptyList();}  
	default public <T2> T2 foldLeft(T2 seed,BiFunction<T2,T,T2> f) {return seed;}
	default public <T2> T2 foldRight(T2 seed,BiFunction<T2,T,T2> f) {return seed;}
    default public list<T> takeWhile(Predicate<T> p){ return emptyList();}
    default public <T2> list<T2> map2(Function<T,T2> f){ return emptyList();}
    default public list<T> concat(list<T> l){return l;}

}
//instance empty list
public final static list<?> EMPTY=new list<Object>() {
public Object head() {
	throw new RuntimeException();
}	

public list<Object> tail() {
 	throw new RuntimeException();	
}

public String toString() {
	return "";
}
};
public static <T> list<T> emptyList(){ return (list<T>)EMPTY;}
public static <T> list<T> list(T head,list<T> tail){ return new NEL(head,tail);}
public static <T> list<T> list(T... t){  return list(0,t.length,t);   }
public static <T> list<T> list(int i,int j,T... t){
	if(i==j) return emptyList();
	return list(t[i],list(i+1,j,t));
}

public static class NEL<T> implements list<T>{
	public final T head;
	public final list<T> tail;
	private NEL(T head,list<T> tail) {
	this.head=head;
	this.tail=tail;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T head() {
		// TODO Auto-generated method stub
		return head;
	}

	@Override
	public list<T> tail() {
		// TODO Auto-generated method stub
		return tail;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1+tail().size();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return head()+","+tail().toString();
	}
	@Override
	public boolean contains(T e) {
		// TODO Auto-generated method stub
		if(head().equals(e)) return true;
		return tail().contains(e);
	}
	
	public static double sum(list<Integer> l) {
		return l.isEmpty()? 0 : l.head()+sum(l.tail());
	}
	public static double prod(list<Integer> l) {
		return l.isEmpty()? 1 : l.head()*prod(l.tail());
	}
	public static <T> list<T> take(int n,list<T> l){
		if(l.isEmpty() || n<=0) return emptyList();
		return list(l.head(),take(n-1,l.tail()));
	}
	
	public static <T> Optional<T> max(list<T> l,Comparator<T> cmp) {
		if(l.isEmpty())  throw new RuntimeException();
		if(l.size()==1) return Optional.of(l.head());
		Optional<T> max=max(l.tail(),cmp);
		if(cmp.compare(l.head(), max.get())>0) return Optional.of(l.head());
		return max;
	}
	
	public static <T extends Comparable<? super T>> T max(list<T> l) {
		if(l.isEmpty()) throw new RuntimeException();
		if(l.size()==1) return l.head();
		T max=max(l.tail());
		if(max.compareTo(l.head())>0) return max;
		return l.head();
	}
	
	public static <T> T min(list<T> l,Comparator<T> cmp) {
		if(l.isEmpty())  throw new RuntimeException();
		if(l.size()==1) return l.head();
		T min=min(l.tail(),cmp);
		if(cmp.compare(l.head(), min)<0) return l.head();
		return min;
	}
	public static <T extends Comparable<? super T>> T min(list<T> l) {
		if(l.isEmpty())  throw new RuntimeException();
		if(l.size()==1) return l.head();
		T min=min(l.tail());
		if(min.compareTo(l.head())>0) return l.head();
		return min;
	}
	
	public static <T> list<T> drop(int n,list<T> l){
		if(l.isEmpty()||n<=0) return l;
		return drop(n-1,l.tail());
	}
	
	public static <T> list<T> purger(list<T> l){
		if(l.isEmpty()) return l;
		if(l.tail().contains(l.head())) return purger(l.tail());
		return list(l.head(),purger(l.tail()));
	}
	
	public static <T> list<T> concat(list<T> l1,list<T> l2){
		if(l1.isEmpty()) return l2;
		if(l2.isEmpty()) return l1;
		return list(l1.head(),concat(l1.tail(),l2));
	}
	
	public static <T> list<T> reverser(list<T> l){
		if(l.isEmpty())  return l;
		return concat( reverser(l.tail()),list(l.head()));
	}
	
	public static <T> list<T> flat(list<list<T>> l){
		if(l.isEmpty()) return l.head();
		return concat(l.head(),flat(l.tail()));
	}
	public static <T> list<T> delOne(list<T> l,T x){
	if(l.isEmpty())	 return l;
	if(l.head().equals(x))  return l.tail();
	return list(l.head(),delOne(l.tail(),x));
	}
	
	public static <T> list<T> delAll(list<T> l,T x){
		if(l.isEmpty())	 return l;
		if(l.head().equals(x))  return delAll(l.tail(),x);
		return list(l.head(),delAll(l.tail(),x));
		}
	public static <T> Paire<list<T>> split(list<T> l){
		if(l.isEmpty()) return new Paire<list<T>>(emptyList(),emptyList());
		return new Paire(take(l.size()/2,l),drop(l.size()/2,l));
	}
	
	public static <T> Paire<list<T>> sp(list<T> l){
		if(l.isEmpty()) return new Paire(emptyList(),emptyList() );
		Paire<list<T>> p=sp(l.tail());
		return new Paire(list(l.head(),p.second),p.first);
	}
	
	public static <T extends Comparable<? super T>> list<T> fusionner(list<T> l1,list<T> l2){
		if(l1.isEmpty()) return l2;
		if(l2.isEmpty()) return l1;
		if(l1.head().compareTo(l2.head())>0) return list(l2.head(),fusionner(l1,l2.tail()));
		return list(l1.head(),fusionner(l1.tail(),l2));
		
	}
	
	public static <T extends Comparable<? super T>> list<T> ins(T x,list<T> l){
		if(l.isEmpty()) return list(x,emptyList());
		if(x.compareTo(l.head())<0) return list(x,l);
		return list(l.head(),ins(x,l.tail()));
	}
	
	//trie par fusion 
	public static <T extends Comparable<? super T>> list<T> Msort(list<T> l){
		if(l.isEmpty()) return emptyList();
		Paire<list<T>> p=sp(l);
		return fusionner(Msort(p.getFirst()),Msort(p.getSecond()));
	}
	//trie par insertion
	public static <T extends Comparable<? super T>> list<T> Isort(list<T> l){
		if(l.isEmpty()) return emptyList();
		return ins(l.head(),Isort(l.tail()));
	}
	
	//trie par selection
	public static <T extends Comparable<? super T>> list<T> Ssort(list<T> l){
		if(l.isEmpty()) return emptyList();
		T x=min(l);		
		return list(x,Ssort(delOne(l,x)));
	}
	//My sort
	public static <T extends Comparable<? super T>> list<T> Sort(list<T> l){
		if(l.isEmpty())  return l;
		return Sort(l.tail(),l.head(),emptyList());
	}
	private static <T extends Comparable<? super T>> list<T> Sort(list<T> l,T m,list<T> r)
	{ if(l.isEmpty()) return list(m,Sort(r));
	  if(l.head().compareTo(m)<0) return Sort(l.tail(),l.head(),list(m,r));
	  return Sort(l.tail(),m,list(l.head(),r));
	}
	
	
	//average
	
	public static double avg(list<Integer> l) {
		return avg(l,0,0);
	}
	
	private static double avg(list<Integer> l,double a,int s) {
		if(l.isEmpty()) return a/s;
		return avg(l.tail(),l.head()+a,s+1);
	}
	
	//reverserP
	public static <T> list<T> reverserP(list<T> l){
		
	return reverserP(l,emptyList());	
	}
	private static <T> list<T> reverserP(list<T> l,list<T> r){
		if(l.isEmpty())  return r;
	return reverserP(l.tail(),concat(list(l.head()),r));	
	}
	
	/////Fold/////
	
	//Fold Left
	
	public T FoldL(T e ,BinaryOperator<T> f){
		return tail().FoldL(f.apply(head(), e),f);	
	}
	
	//Fold Right
	
	public T FoldR(T e ,BinaryOperator<T> f){
		return f.apply(head(), tail().FoldR(e, f));	
	}
	//Fold Left with Bifunction
	
	public <U> U FoldL(U e,BiFunction<T,U,U> f) {
		return tail().FoldL(f.apply(head(),e ),f);
	}
	//Fold Right with Bifunction
	public <U> U FoldR(U e,BiFunction<T,U,U> f) {
		return f.apply(head(), tail().FoldR(e, f));
	}
	
	//sum Fold left
	public int sumFoldLeft(list<Integer> l) {
	return l.FoldL(0, Integer::sum);
	}
	
	//sum Fold right
	public int sumFoldRight(list<Integer> l) {
	return l.FoldR(0, Integer::sum);
	}
	
	//pro Fold left
	public int proFoldLeft(list<Integer> l) {
	return l.FoldL(1, (a,b)->a*b);
	}
	
	//pro Fold left
	public int proFoldRight(list<Integer> l) {
	return l.FoldR(1, (a,b)->a*b);
	}
	// Or Fold Left
	public boolean OrFold(list<Boolean> l) {
		return l.FoldL(false, (a,b)->a|b);
	}
	
	// Or Fold Left
	public boolean AndFold(list<Boolean> l) {
			return l.FoldL(true, (a,b)->a&&b);
	}
	//add  version 1
	public int add(BinaryOperator<Integer> f,int x,int y) {
		return f.apply(x, y);
	}
	//add version 2
	public int add(Function<Integer,Function<Integer,Integer>> f,int x,int y) {
		return f.apply(x).apply(y);
	}
	// sum with add version 2
	public int sumXY(int x,int y) {
	return  add((a)->(b)->x+y,x,y);
	}
	//size with Fold
	public int sizeFold() {
	return FoldL(0,(a,b)->b+1);
	}
	//concat fold Right
	public list<T> concatFoldRight(list<T> l1,list<T> l2){

				
		return l1.FoldR(l2,(x,l)->list(x,l) );	
	}
	//reverser fold right
	public list<T> reverserFoldRight(list<T> l){
		return l.FoldR(emptyList(),(x,r)->snoc(r,x));
	}
	//contraire de list()
	public list<T> snoc(list<T> l,T x){
		return concatFoldRight(l,list(x));
	}
	
	//filter
	public list<T> filter(Predicate<T> p){
		if(p.test(head())) return list(head(),tail().filter(p));
		return tail().filter(p);
	
	}
	
	//filter
	public static <T> list<T> filter(Predicate<T> p,list<T> l){
		if(p.test(l.head())) return list(l.head(),l.tail().filter(p));
		return l.tail().filter(p);
		
	}
	//filter with Fold right 
	public list<T> filterFoldRight(Predicate<T> p,list<T> l){
	return l.FoldR(emptyList(), (x,r)->p.test(x)? list(x,r): r);
	}
	
	//filter with Fold left 
	public list<T> filterFoldLeft(Predicate<T> p,list<T> l){
	return l.FoldL(emptyList(), (x,r)->p.test(x)? snoc(r,x): r);
	}
	
	//QuickSort
	
	public static <T extends Comparable<? super T>> list<T> Qsort(list<T> l){
		  if(l.isEmpty())  return l;
		return concat(Qsort(l.tail().filter(p->p.compareTo(l.head())<0)),
					  list(l.head(),Qsort(l.tail().filter(p->p.compareTo(l.head())>=0)))
					 );
	}
	
	public  <T extends Comparable<? super T>> list<T> Qsort2(list<T> l){
		 if(l.isEmpty())  return l;

		return  concat(Qsort2(l.tail()).filter(p->p.compareTo(l.head())<0),
				  list(l.head(),Qsort2(l.tail()).filter(p->p.compareTo(l.head())>=0))
				 );
	}
	//create list of range values
	public list<Integer> range(int i,int j){
		if(j==i) return emptyList();
		return list(i,range(i+1,j));
	}
	//nombre est premier
	public boolean estPremier(int n) {
		return range(2,n).filter(p->p%2==0).isEmpty();
	}
	//soustrire les nombre premier
	public list<Integer> lesNpremier(int n){
		return range(2,n).filter(p->estPremier(p));
	}
	//map
	public list<T> map(Function<T,T> f){  
		return list(f.apply(head()),tail().map(f));
	}
	//map with Fold
	public list<T> mapFoldRight(Function<T,T> f){  
		return FoldR(emptyList(), (x,l)->list(f.apply(x),l));
	}
	
	
    //
	public <T2> T2 foldLeft(T2 seed,BiFunction<T2,T,T2> f) {
	return  tail().foldLeft(f.apply(seed,head()), f);	
	}
	//
	public <T2> T2 foldRight(T2 seed,BiFunction<T2,T,T2> f) {
	return f.apply(tail().foldRight(seed, f), head());	
	}
	public list<T> takeWhile(Predicate<T> p){
		if(p.test(head())) return list(head(),tail().takeWhile(p));
		return emptyList();
	}
	
	public <T2> list<T2> map2(Function<T,T2> f){
		return list(f.apply(head()),tail().map2(f));
	}
	public list<T> concat(list<T> l){
		return list(head(),tail().concat(l));
	}
	
	
	public static <T2 extends Comparable<T2>> boolean triea(list<T2> l) {
		    if(l.isEmpty())  	  return true;
		    if(l.head()!=min(l))  return false;
		return triea(l.tail());
	}
	
	public static <T extends Comparable<? super T>> list<T> QSort(list<T> l){
	    if(l.isEmpty())  return emptyList();
		return QSort((l.tail().filter(p->p.compareTo(l.head())<0))).concat(
				list(l.head(),
				QSort(l.tail().filter(p->p.compareTo(l.head())>0)))
				);
	}
	
	public static <T> boolean equals(list<T> l1,list<T> l2) {
		if(l1.isEmpty() || l2.isEmpty())  return l1.size()==l2.size();
		if(l1.head().equals(l2.head())) return equals(l1.tail(),l2.tail());
		return false;
	}
	
	
}
 
public static  class Paire<T>{
	private T first,second;

	public Paire() {
		super();
	}

	public Paire(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	

	@Override
	public String toString() {
		return "Paire [first=" + first + ", second=" + second + "]";
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}
}

}
