0   :   MOV [0] 43  ;   value1
3   :   JEQ 10 [0] 43 ;   if value1 == 43 go to instruction 16
7   :   DPRINT 48
9   :   HALT
10  :   JEQ 17 [0] 79 ;   if value1 == 79 go to instruction 29
14  :   DPRINT 752
16  :   HALT
17  :   DPRINT 69
19  :   HALT