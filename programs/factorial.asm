0:mov [0] 3
3:mov [1] 0
6:jgt 14 [0] 0
10:mov [0] [1]
13:halt
14:mov [3] [0]
17:mov [2] [0]
20:mov [1] 0
23:jz 34 [3]
26:add [1] [2]
29:sub [3] 1
32:jmp 23
34:sub [0] 1
37:jmp 6