package test;

import junit.framework.TestCase;
import us.softoption.parser.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import static us.softoption.infrastructure.Symbols.*;

/*should run*/

public class CCParserTest10 extends TestCase {
	
	TFormula fRoot;
	TParser fParser;
	ArrayList fValuation=new ArrayList();
	Reader fReader = new StringReader("L");
	CCParser fCCParser;
	
	String notAbandBc= "("+chNeg+"Ab"+ chAnd +"Bc)";


	String allxNotAyandBcOrChookDfhzOrCfofgofdEquivnotd= "("+chUniquant+   "x)"+
	   "(("+chNeg+"Ay"+ chAnd +"Bc)"+
	   chOr +
	   "((C"+ chImplic +"Df(hz))"+
	   chOr +
	   "(Cf(g(d))"+ chEquiv +chNeg+"D)))";
		
	String FZero= "F0";
	String oneEqualsTwo= "1=2";
	String oneEqualsSuccTwo= "1=2'";
	String oneEqualsSuccSuccTwo= "1=2''''";
	String FZeroTimes1= "F(0.1)";
	String oneEquals2Times3= "1=2.3";
	String oneEquals2Times3Bracket= "1=(2.3)";
	String oneEqualsSuccTwoTimesThree= "1=(2'.3)";

	String xEpsilony= "x" +chMemberOf + "y";
	String xEpsilonyEpsilonz= "x" +chMemberOf + "y"+chMemberOf + "z";
	String xplusEpsilony= "x+1" +chMemberOf + "y";
	String xplusEpsilonyBracket= "(x+1)" +chMemberOf + "y";
	String xEpsilonyPlus= "x" +chMemberOf + "(y+1)";
	String xEpsilonyUniony= "x" +chMemberOf + "(y"+chUnion+"z)";
	String xEpsilonyIntersectiony= "x" +chMemberOf + "(y"+chIntersection+"z)";
	String xEpsilonEmpty= "x" +chMemberOf + strEmptySet;
	String xEpsilonUniverse= "x" +chMemberOf + strUniverseSet;
	String xEpsilonEmptyComp= "x" +chMemberOf + "{}";
	String xEpsilonComp1= "x" +chMemberOf + "{1}";
	String xEpsilonComp123= "x" +chMemberOf + "{1,2,3}";
	String Fx = "Fx";
	String xEpsilonCompFx= "x" +chMemberOf + "{x|Fx}";
	String xEpsilonCompFx2= "x" +chMemberOf + "{x:(Fx" +chAnd+ "Gy)}";
	String xNotEpsilonCompFx2= "x" +chNotMemberOf + "{x:(Fx" +chAnd+ "Gy)}";

	String EmptyEpsilonEmpty= strEmptySet +chMemberOf + strEmptySet;
	String CurlyEpsilonEmpty= "{}" +chMemberOf + strEmptySet;
	String CompEpsilonCompFx= "{x|Fx}" +chMemberOf + "{x|Fx}";
	String CompEpsilonCompFx2= "{x:(Fx" +chAnd+ "Gy)}" +chMemberOf + "{x:(Fx" +chAnd+ "Gy)}";
	String CompNotEpsilonCompFx2= "{x:(Fx" +chAnd+ "Gy)}" +chNotMemberOf + "{x:(Fx" +chAnd+ "Gy)}";

	String Extensionality= "("+ chUniquant + "x)(" + chUniquant + "y)((x=y)"+
	                        chEquiv+"("+ chUniquant +"z)((z"+chMemberOf +"x)"+chEquiv+"(z"+chMemberOf +"y)))";
		

	String aMinusb= "(a"+chMinus +"c)=c";

	String xEpsilonPowery= "x" +chMemberOf + chPowerSet + "(y)";
	String xEpsilonPoweryz= "x" +chMemberOf + chPowerSet + "(yz)";

	String FSimpleOrderedPair= "F<p,q>";
	String xEpsilonSimpleOrderedPair= "x" +chMemberOf + "<p,q>";
	String xEpsilonOrderedPair= "x" +chMemberOf + chPowerSet + "(<p,q>)";

	String orderedEpsilonX= "<p,q>" +chMemberOf + "(a" +chXProd+ "b)";

	String copi= "(x)Fx";

	String xEpsilonX= "x" +chMemberOf + "(a" +chXProd+ "b)";

	String upDown= strUpTack+ chEquiv + strDownTack;

	String xSuperscript= "Fx" + chSuperscript1;
	
	String emptyComprehension = "{}";
	String nonEmptyComprehension = "{a,b,c}";
	
	
	
	public void generic(String before){
		fParser=new TParser();		
		String beforeStr= before;
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.wffCheck();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);
		
	
		sr = new java.io.StringReader( beforeStr );
		r = new java.io.BufferedReader( sr );
	fParser.wffCheck(fRoot, sr);
	
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);

    assertEquals(beforeStr, afterStr);
	
	
	}
	
public void genericTerm(String before){
		
		fParser=new TParser();		
		String beforeStr=before;
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);	
	}
	

	public void testConstant(){
		
		fParser=new TParser();		
		String beforeStr="5";
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);	
	}
	
	public void testConstant2(){
		
		fParser=new TParser();		
		String beforeStr="\u2205";
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);	
	}	
	
	public void testFunctor(){
		
		fParser=new TParser();		
		String beforeStr="v";
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);	
	}		
	
	public void testInnerTerm(){
		
		fParser=new TParser();		
		String beforeStr="(z)";
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, "("+afterStr+")");	
	}
	
public void testSubscript(){
		
		fParser=new TParser();		
		String beforeStr="z"+ "\u2081"+ "\u2082";
		String afterStr="";		
		java.io.StringReader sr = new java.io.StringReader( beforeStr );
		java.io.Reader r = new java.io.BufferedReader( sr );		
		fCCParser = new CCParser(sr);
		
		try {
			fRoot= fCCParser.term();
	    } catch (ParseException e) {
	        fRoot=null;
	        System.out.println("Not parsed");
	    }
				
		if (fRoot!=null)
			afterStr=fParser.writeFormulaToString(fRoot);

	    assertEquals(beforeStr, afterStr);
	    
	  //  System.out.println(afterStr);
	}

public void testFunctions(){
	
	fParser=new TParser();		
	String beforeStr="f(gh)";  //works with comma
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) { 
        fRoot=null;
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);

    assertEquals(beforeStr, afterStr);
}

public void testOrderedPair(){
	
	fParser=new TParser();		
	String beforeStr="<g,h>";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
    assertEquals(beforeStr, afterStr);
    
}



public void testPowerSet(){
	
	fParser=new TParser();		
	String beforeStr= ""+"\u2118"+"(a)";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
  //      System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
    //System.out.println("After: "+afterStr);
    //System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
    //System.out.println("After: "+afterStr);
}

public void testComprehension1(){
	
	fParser=new TParser();		
	String beforeStr= emptyComprehension;
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
       // System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
    //System.out.println("After: "+afterStr);
    //System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
    //System.out.println("After: "+afterStr);
}



public void testComprehension2(){
	
	fParser=new TParser();		
	String beforeStr= nonEmptyComprehension;
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testSuccessor(){
	
	fParser=new TParser();		
	String beforeStr= "a'";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
	
	
  // System.out.println("AfterSucc: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testMult(){
	
	fParser=new TParser();		
	String beforeStr= "(a''.b)";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testPlusMinus(){
	
	fParser=new TParser();		
	String beforeStr= "((a''.b)+(0-1))";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}



public void testIntersection(){
	
	fParser=new TParser();		
	String beforeStr= "(a"+"\u2229"+"b)";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.term();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}




// ********************  testing WFF************

public void testAtomicTopBottom(){
	
	fParser=new TParser();		
	String beforeStr= "\u22A4";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.wffCheck();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testPredicate(){
	
	fParser=new TParser();		
	String beforeStr= "Qabcd";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.wffCheck();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testPredicate2(){
	
	fParser=new TParser();		
	String beforeStr= "Q"+"\u2118"+"(d)";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.wffCheck();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testPredicate3(){
	
	fParser=new TParser();		
	String beforeStr= "Q(1+1)<b,c>"+"\u2118"+"(d)";
	String afterStr="";		
	java.io.StringReader sr = new java.io.StringReader( beforeStr );
	java.io.Reader r = new java.io.BufferedReader( sr );		
	fCCParser = new CCParser(sr);
	
	//System.out.println("Before Str "+beforeStr);
	
	try {
		fRoot= fCCParser.wffCheck();
    } catch (ParseException e) {
        fRoot=null;
        System.out.println("Not parsed");
    }
			
	if (fRoot!=null)
		afterStr=fParser.writeFormulaToString(fRoot);
	
   // System.out.println("After: "+afterStr);
   // System.out.println(fRoot.toString());

    assertEquals(beforeStr, afterStr);
    
  //  System.out.println("After: "+afterStr);
}

public void testInfixPredicate(){
	//  /*<term1> =<term2> or whatever
	//  /*<term1> epsilon <setTheoryterm2> or whatever/
	//  /*<term1> not epsilon <setTheoryterm2> SPECIAL CASE
	
	generic("1=2");
	generic("1"+"\u2208"+"2");
	generic("1"+"\u2209"+"2");
	generic("1<2");
	generic("1>2");

}


 
public void testSecondary(){

	
	generic("\u223C"+"P");
	generic("\u25CA"+"P");
	generic("\u25A1"+"P");
	generic("Fx");
	generic("("+"\u2203"+"x)Fx");
	generic("("+"\u2203"+"x:a)Fx");  //typed
	generic("("+"\u2203!"+"x)Fx");   // unique
	generic("("+"\u2203!"+"x:a)Fx");  //unique typed
	generic("("+"\u2200"+"x)Fx");
	generic("("+"\u2200"+"x:a)Fx");  //typed
	//generic("(x)Fx"); // uniquant no quant
	//generic("(x:a)Fx");  //typed
	generic("1>2");
//	generic("P&D");  OK
//	generic("P.D");  OK
	generic("P"+"\u2261"+"D");
	generic("P"+"\u2227"+"D");
//	generic("P"+"\u2194"+"D");  OK

}  

public void testSubscript2(){
	
	generic("C"+ "\u2081" +"\u2082"+"\u2083");


} 

public void testMore1(){
	
//	genericTerm("(a+1)");
	//genericTerm("a+1"); OK
//	generic("a=y");
	generic("(a+1)=y");
//	generic("(x+1)=y");
	
	generic("C"+ chEquiv +chNeg+"D");
	generic("Cf"+ chEquiv +chNeg+"D");
	generic("Cf(d)"+ chEquiv +chNeg+"D");
//	generic("(C)");  OK
//	generic("(Cf(d))"+ chEquiv +chNeg+"D");  OK
	generic("A"+ "\u2261" +"B");
//	generic("(A"+ "\u2261" +"B)");  OK
	generic("A"+ "\u2261"+chNeg +"B");
	generic("Ad"+ "\u2261"+chNeg +"B");
	generic("Ag(d)"+ "\u2261"+chNeg +"B");
//	generic("(Ag(d)"+ "\u2261"+chNeg +"B)");  OK
	generic("Cf(g(d))"+ chEquiv +chNeg+"D");
	generic(allxNotAyandBcOrChookDfhzOrCfofgofdEquivnotd);
	generic(oneEqualsTwo);
	generic(oneEqualsSuccTwo);
	generic(oneEqualsSuccSuccTwo);
	generic(oneEquals2Times3Bracket);
	generic(oneEqualsSuccTwoTimesThree); 
	generic(xEpsilony);   // unique
	//generic(xplusEpsilony); OK
	//generic(xEpsilonyEpsilonz);  NOT a WFF
	//generic(xplusEpsilony);  OK
	generic("1>2");
	//generic("P&D");  OK
	//generic("P.D");  OK
	generic("P"+"\u2261"+"D");
//	generic("P"+"\u2194"+"D"); OK
	generic("(x+1)=y");
	generic(xplusEpsilonyBracket);
	generic(xEpsilonyPlus);
	generic(xEpsilonyUniony);
	generic(xEpsilonyIntersectiony);
	generic(xEpsilonEmpty); 

}

public void testMore2(){
	generic("("+chUniquant+"x)Fx");
	generic("("+chUniquant+"x:a)Fx");
//	generic("(x)Fx"); // OK
//	generic("(x:a)Fx");  //OK
	generic(xplusEpsilonyBracket); 
	generic(xEpsilonyPlus); 
	generic(xEpsilonyUniony); 
	generic(xEpsilonyIntersectiony); 
	generic(xEpsilonEmpty); 
	
	generic("("+chUniquant+"x)Fx"); 
	//generic("(x)Fx"); OK
	
	generic("("+chUniquant+"x:a)Fx"); 
	generic("Fx"); 
	 
	
}

public void testTop(){
	generic("Fxyf(s)"+chEquiv+"Gx");
	generic("(Fx"+chEquiv+"Gx)"+chEquiv+"Hx");
	generic("Fx"+chEquiv+"(Gx"+chEquiv+"Hx)");
	generic("(Fx"+chAnd+"Gx)"+chOr+"Hx");
	generic("Fx"+chImplic+"(Gx"+chEquiv+"Hx)");
}	
	
	
public void testNegation(){
	generic(chNeg+"F");
	generic(chNeg+""+chNeg+"F");
	//generic("~~~~~F");
	generic(chNeg+(chNeg+"F"));
	generic("\u25CA"+"\u25CA"+chNeg+chNeg+"F");
	generic("\u25CA"+"\u25CA"+"\u223C"+"\u223C"+"F");
	generic("\u223C"+"\u223C"+"F");
	

	
	
	
}	
	
public void testTypeComprehension(){
	generic("("+chExiquant+"x)F");
	generic("("+chExiquant+"x:t)Fx");  //CompEpsilonCompFx
	//generic("("+chExiquant+"x:"+"{}"+")Fx");
	//generic("("+chExiquant+"x:"+"{a,b,c}"+")Fx");
	generic("("+chExiquant+"x:"+"{x:Fx}"+")Fx"); //{x:(Fx" +chAnd+ "Gy)}
	generic("("+chExiquant+"x:"+"{x:(Fx" +chAnd+ "Gy)}"+")Fx");

	generic("("+chUniquant+"x)F");
	generic("("+chUniquant+"x:t)Fx");  //CompEpsilonCompFx
	//generic("("+chUniquant+"x:"+"{}"+")Fx");
	generic("("+chUniquant+"x:"+"{x:Fx}"+")Fx"); //{x:(Fx" +chAnd+ "Gy)}
	generic("("+chUniquant+"x:"+"{x:(Fx" +chAnd+ "Gy)}"+")Fx");	

	
	
	
}		
	
public void testOddAnds(){
	//generic("F.G");
	//generic("Ft.Gs");
	//generic("Ft.s");
	generic("F(t.s)");


	
	
	
}	

}
