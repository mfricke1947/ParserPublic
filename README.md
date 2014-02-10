ParserPublic
============

These are the parsers used for propositional and predicate logic on http://softoption.us .

There are, possibly, four parsers.

There are hand-written recursive descent parsers for the basic propositional and predicate logic, and 
for lambda calculus.

Then there are two automatically generated parsers CCParser and CCParserTwo also for logic.

All the actual parsers in use for the logic in the instructional software, and subclassed parsers 
all use CCParser or CCParserTwo.

They can parse pretty well all the standard versions of logic (versions corresponding to all the
standard textbooks). There is a single internal form, and the variations are translated into it when 
being read and parsed, then the formulas are written back in the desired flavor or style.
