package com.ouder.list;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ouder.list.Mlist.NEL;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		NEL<Integer> list=(NEL<Integer>) Mlist.list(new Integer[] {1,6,2,6,3,4});
		System.out.println("list : "+list);
		System.out.println("min : "+list.min(list, Integer::compare));
		System.out.println("max : "+list.max(list, Integer::compare).get());
		System.out.println("take : "+list.take(2,list));
		System.out.println("drop : "+list.drop(2,list));
		System.out.println("sum : "+list.sum(list));
		System.out.println("prod : "+list.prod(list));
		System.out.println("purger : "+list.purger(list) );
		System.out.println("concat: "+list.concat(list,list) );
		System.out.println("reverser: "+list.reverser(list) );
		NEL<NEL<Integer>> listFlat=(NEL<NEL<Integer>>) Mlist.list(list,list);
		System.out.println("flat : "+listFlat);
		System.out.println("delOne : "+list.delOne(list, 3));
		System.out.println("delAll : "+list.delAll(list, 3));
		System.out.println("split : "+list.split(list));
		System.out.println("sp : "+list.sp(list));
		System.out.println("fusionner : "+list.fusionner(list.sp(list).getSecond(),list.sp(list).getFirst()));
		System.out.println("trie par fusion (Msort) : "+list.Msort(list));
		
		System.out.println("trie par selection (Ssort) : "+list.Ssort(list));
		System.out.println("trie  (Sort) : "+list.Sort(list));
		System.out.println("average  : "+list.avg(list));
		System.out.println("reverserP  : "+list.reverserP(list));
		System.out.println("sum fold left  : "+list.sumFoldLeft(list));
		System.out.println("sum fold right  : "+list.sumFoldRight(list));
		System.out.println("produit fold left  : "+list.proFoldLeft(list));
		System.out.println("produit fold right  : "+list.proFoldRight(list));
		System.out.println("sum with add version 2 : "+list.sumXY(4, 5));
		System.out.println("size with Fold : "+list.sizeFold());
		System.out.println("concat Fold Right: "+list.concatFoldRight(list,list) );
		System.out.println("reverser fold right: "+list.reverserFoldRight(list));
		System.out.println("filter : "+list.filter(p->p>3));
		System.out.println("filter static : "+list.filter(p->p>3,list));
		System.out.println("filter fold right: "+list.filterFoldRight(p->p>3,list));
		System.out.println("filter fold left : "+list.filterFoldLeft(p->p>3,list));
		
		double s2=System.currentTimeMillis();
		System.out.println("QuickSort : "+list.Qsort(list));
		double e2=System.currentTimeMillis();
		System.out.println("time quickSort "+( e2-s2));

		
		double s1=System.currentTimeMillis();
		System.out.println("trie par insertion (Isort) : "+list.Isort(list));
		double e1=System.currentTimeMillis();
		System.out.println("time trie par insertion "+( e1-s1));
		System.out.println("map : "+list.mapFoldRight(p->p+1));
		System.out.println("map  with Fold : "+list.mapFoldRight(p->p+1));
		
		System.out.println("takeWhile : => "+list.takeWhile(p->p<7));
		System.out.println("concat : => "+list.concat(list));
		System.out.println("triea : => "+list.triea(list.QSort(list)));
		System.out.println("quick Sort : => "+list.QSort(list));
		
		System.out.println("equals : => "+list.equals(list,list));








		
  
	}  
	

}
