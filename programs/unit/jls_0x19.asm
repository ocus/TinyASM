0   :   MOV [0] 25  ;   value1
3   :   MOV [1] 7   ;   value2
6   :   MOV [2] 54  ;   value3
9   :   JLS 16 [0] [2] ;   if value1 < value3 go to instruction 16
13  :   DPRINT 756
15  :   HALT
16  :   JLS 23 [0] [1] ;   if value1 < value2 go to instruction 29
20  :   DPRINT 978
22  :   HALT
23  :   DPRINT 417
25  :   HALT