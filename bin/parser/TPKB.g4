
//http://stackoverflow.com/questions/15610183/if-else-statements-in-antlr-using-listeners

grammar TPKB;

tpkb 
: tpkb_class+
;

tpkb_class
 : CLASS ID OBRACE 
 tpkb_class_subclass
 tpkb_class_subpart
 tpkb_class_attribute
 CBRACE
 ;
 
tpkb_class_subclass
 : (SUBCLASSES (ID FLOAT COMMA)* ID FLOAT SEMI | )
 ;
 
tpkb_class_subpart
: (PARTS (ID ID COMMA)* ID ID SEMI | )
;  

tpkb_class_attribute
: (ATTRIBUTES (ID (DBTOKEN | CSVTOKEN) COMMA)* ID (DBTOKEN | CSVTOKEN) SEMI | )
;


DBTOKEN : 'IDB';
CSVTOKEN : 'ICSV';

COMMA : ',';
SEMI : ';';
CLASS : 'Class';
OR : '||';
AND : '&&';
EQ : '==';
NEQ : '!=';
GT : '>';
LT : '<';
GTEQ : '>=';
LTEQ : '<=';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
MOD : '%';
POW : '^';
NOT : '!';

SCOL : ';';
ASSIGN : '=';
OPAR : '(';
CPAR : ')';
OBRACE : '{';
CBRACE : '}';

TRUE : 'true';
FALSE : 'false';
NIL : 'nil';

ATTRIBUTES : 'attributes';
SUBCLASSES : 'subclasses';
PARTS : 'parts';

ID
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;
 
INT
 : [0-9]+
 ;

FLOAT
 : [0-9]+ '.' [0-9]* 
 | '.' [0-9]+
 ;

STRING
 : '"' (~["\r\n] | '""')* '"'
 ;

COMMENT
 : '#' ~[\r\n]* -> skip
 ;

SPACE
 : [ \t\r\n] -> skip
 ;

OTHER
 : . 
 ;