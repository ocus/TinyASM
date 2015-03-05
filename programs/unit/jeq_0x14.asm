0   :   MOV [0] 71  ;   value1
3   :   MOV [1] 7   ;   value2
6   :   MOV [2] 71  ;   value3
9   :   MOV [3] 22      
12  :   MOV [4] 29      
15  :   JEQ [3] [0] [2] ;   if value1 == value3 go to instruction [3]
19  :   DPRINT 100      
21  :   HALT        
22  :   JEQ [4] [0] [1] ;   if value1 == value2 go to instruction [4]
26  :   DPRINT 200      
28  :   HALT        
29  :   DPRINT 66       
31  :   HALT        