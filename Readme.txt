Owen Radcliffe
677414365

Section I
	To compile and execute the code, import the project into an IDE.
	Modify the run configuration to set the number of buffers in the buffer pool.
	Run the program and enter GET, SET, PIN, or UNPIN commands. To exit the shell, input EXIT.
	For the SET command, the string of 40 bytes should be enclosed by quotation marks.

Section II
Test Case Results:
Command 2, Case 2 - Pass
Command 1, Case 1 - Pass
Command 1, Case 2 - Pass
Command 2, Case 1 - Pass
Command 3, Case 1 - Pass
Command 4, Case 2 - Pass
Command 1, Case 1 - Pass
Command 3, Case 1 - Pass
Command 1, Case 2 - Pass
Command 3, Case 2 - Pass
Command 2, Case 3 - Pass
Command 4, Case 1 - Pass
Command 1, Case 3 - Pass
Command 3, Case 2 - Pass
Command 1, Case 1 - Pass
Command 2, Case 4 - Pass
Command 3, Case 3 - Pass
Command 1, Case 4 - Pass
Command 4, Case 1 - Pass
Command 4, Case 1 - Pass
Command 1, Case 3 – Pass
Command 3, Case 2 - Pass

No tests failing.

Section III
      I added a variable to the BufferPool class which is an integer tracking the last evicted frame number for the eviction policy method. 
