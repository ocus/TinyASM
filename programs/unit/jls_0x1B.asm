0   :   MOV [0] 23  ;   value1
3   :   JLS 10 [0] 43 ;   if value1 < 43 go to instruction 16
7   :   DPRINT 78
9   :   HALT
10  :   JLS 17 [0] 11 ;   if value1 < 11 go to instruction 29
14  :   DPRINT 945
16  :   HALT
17  :   DPRINT 352
19  :   HALT