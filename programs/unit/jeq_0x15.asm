0   :   MOV [0] 71  ;   value1
3   :   MOV [1] 7   ;   value2
6   :   MOV [2] 71  ;   value3
9   :   JEQ 16 [0] [2] ;   if value1 == value3 go to instruction 16
13  :   DPRINT 100
15  :   HALT
16  :   JEQ 23 [0] [1] ;   if value1 == value2 go to instruction 29
20  :   DPRINT 200
22  :   HALT
23  :   DPRINT 66
25  :   HALT