0   :   MOV [0] 43  ;   value1
3   :   MOV [1] 13  ;   value2
6   :   MOV [2] 25  ;   value3
9   :   MOV [3] 22      
12  :   MOV [4] 29      
15  :   JLS [3] [0] [2] ;   if value1 < value2 go to instruction [3]
19  :   DPRINT 454
21  :   HALT        
22  :   JLS [4] [0] [1] ;   if value1 < value3 go to instruction [4]
26  :   DPRINT 745
28  :   HALT        
29  :   DPRINT 66       
31  :   HALT        