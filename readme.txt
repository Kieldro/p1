NAMES: Ian Buitrago, Ervin Kalemi
UT EIDS: ib, kalemi19
SECTIONS: 52405, 52395
CODING STATUS: Compiles, Part 1 complete. Part 2 in progress;

*Explanation on why the compression fails on the larger.txt*

The pairs in the cpz aren't reused enough to reduce the size of larger.txt.
For example, if there are only 3 characters in the uncompressed file,
the pairs only relate 2 or 3 characters to each other. Whereas in the in smaller.txt,
compressions works because the pairs use the dictionary many times.
