0   :   MOV [0] 15  ;   value1
3   :   MOV [3] 16
6   :   MOV [4] 23
9   :   JLS [3] [0] 58 ;   if value1 < 58 go to instruction [3]
12  :   DPRINT 475
15  :   HALT
16  :   JLS [4] [0] 8 ;   if value1 < 8 go to instruction [4]
20  :   DPRINT 699
22  :   HALT
23  :   DPRINT 111
25  :   HALT