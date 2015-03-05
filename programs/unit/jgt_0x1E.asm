0   :   MOV [0] 75  ;   value1
3   :   MOV [3] 16
6   :   MOV [4] 23
9   :   JGT [3] [0] 58 ;   if value1 > 58 go to instruction [3]
12  :   DPRINT 963
15  :   HALT
16  :   JGT [4] [0] 99 ;   if value1 > 99 go to instruction [4]
20  :   DPRINT 852
22  :   HALT
23  :   DPRINT 741
25  :   HALT