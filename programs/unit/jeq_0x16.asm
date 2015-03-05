0   :   MOV [0] 58  ;   value1
3   :   MOV [3] 16
6   :   MOV [4] 23
9   :   JEQ [3] [0] 58 ;   if value1 == 58 go to instruction [3]
12  :   DPRINT 47
15  :   HALT
16  :   JEQ [4] [0] 42 ;   if value1 == 42 go to instruction [4]
20  :   DPRINT 123
22  :   HALT
23  :   DPRINT 66
25  :   HALT