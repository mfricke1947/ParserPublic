2/8/2014

Please look at TParser.java

There are, possibly, four parsers.

There are hand-written recursive descent parsers for the basic logic and for lambda calculus.

Then there are two automatically generated parsers CCParser and CCParserTwo also for logic.

All the actual parsers in use for the logic, and subclassed parsers all use CCParser or CCParserTwo.

------------

[earlier, written from 2003-2012]

/* We have four basic parsers here. There was the original recursive descent
parser (and a separate one for the lambda calculus). Then the original system
was moved to an automatic parser generator and two parsers were generated:CCParserOne and
CCParserTwo.  Wherever 'CC' is to be found, it is a generated parser.
*/

/*The recursive descent parser is seen here, but the subclasses of this (i.e. of TParser)
implement the parser generated parsers
*/

/*We want several different parsers of similar structure; we define generic procedures
which are called in the normal way.  Most documents and windows have fParser fields to
ensure the correct reading and writing.*/


/*Nov 8th 2003 Old versions of this used to have the filtering input mechanism written into the code
but Javas regular expression powers allow this to be done to the input before it ever gets to skip
or to the parser*/

/*we have only one internal 'canonical' form for each connective but translate
back and forth when reading or writing*/

/*When reading in, we will usually accept any of the alternative versions of, say, the 'not' symbol
but when writing back we will use the appropriate one*/

/*for the symbols we 'import static' from Symbols*/

/*SET THEORY. For set theory we treat epsilon as an infix predicate so xepsilon y is similar to x=y or x<y .
 * Then we treat union and intersection as infix functors so xuniony is similar to x+y or x.y .
 * 
 *  Note that at the moment we don't have as a matter of semantics that notMemberOf is the negation
 *  of memberOf ie they are independent infix operators
 *
 */

/*Jan 09 we will try to parse everything in one, whether it is ordinary first order, set
 * theory or what.
 * 
 * {old grammar for terms}
{<functor>::= a..z|0|1    }
{<termprimary>::=(<term>)|<functor>|<functor(<nonempty termlist>)  }
{<termsecondary>::= <termprimary><nonempty list of '''>|<termprimary> . <termsecondary>| <termprimary>}
{<termtertiary>::= <termsecondary> + <termtertiary>| <termsecondary>}

{old grammar for SetTheory terms}
{<functor>::= a..z|0|1|2|3  |  } basically variables or constants
{<termprimary>::=(<term>)|<functor>|<setTheory constant eg empty set>|powerSet(term) }
{<termprimary>::={}|{termlist}|{var: scope}|{var| scope} }
{<termsecondary>::= <termprimary><nonempty list of '''>|<termprimary> intersect <termsecondary>| <termprimary>}
{<termtertiary>::= <termsecondary> union <termtertiary>| <termsecondary>}

We will now smush these together (hopefully)

{new grammar for terms}
{<functor>::= a..z|0|1 + <setTheory constant eg empty set>  NOTE MUST INCLUDE PHI AND U AMONG CONSTANTS    }
{<termprimary>::=(<term>)|<functor>|<functor(<nonempty termlist>)||powerSet(singleterm)  }
{<termprimary>::={}|{termlist}|{var: scope}|{var| scope} }
{<termsecondary>::= <termprimary><nonempty list of '''>|<termprimary> . <termsecondary>| <termprimary>}
{<termtertiary>::= <termsecondary> + <termtertiary>| <termsecondary>}
{<term4ary>::= <termtertiary> intersect <term4ary>| <termtertiary>}
 */

/*Note: U is a predicate, and U is also the universe set. So UU is a wff, and so too is PU etc.
 * 
 * This is not ideal, but it should not occur all that often.

Revised 2012 U as the universe set did not work very well, so replaced by the German U-umlaut
 */

/*  This will also parse formulas with alternative logical symbols eg & for ^
 * 
 * 
 */