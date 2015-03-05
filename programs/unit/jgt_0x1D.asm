0   :   MOV [0] 57  ;   value1
3   :   MOV [1] 58   ;   value2
6   :   MOV [2] 56  ;   value3
9   :   JGT 16 [0] [2] ;   if value1 > value3 go to instruction 16
13  :   DPRINT 123
15  :   HALT
16  :   JGT 23 [0] [1] ;   if value1 > value2 go to instruction 29
20  :   DPRINT 456
22  :   HALT
23  :   DPRINT 789
25  :   HALT