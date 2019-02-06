#!/bin/bash  
echo "Running 3 tests for part 1 and 2..."  

echo "Writing test 1 to doc/testResults/part_1_and_2/test_1.txt"
echo "Test 1 (part 1 and 2)" > doc/testResults/part_1_and_2/test_1.txt
echo "Searching BST for: 3 known dam names" >> doc/testResults/part_1_and_2/test_1.txt

java -cp bin DamBSTApp "Lindleyspoort Dam">> doc/testResults/part_1_and_2/test_1.txt
echo " " >> doc/testResults/part_1_and_2/test_1.txt

java -cp bin DamBSTApp "Koppies Dam">> doc/testResults/part_1_and_2/test_1.txt
echo " " >> doc/testResults/part_1_and_2/test_1.txt

java -cp bin DamBSTApp "Woodstock Dam">> doc/testResults/part_1_and_2/test_1.txt
echo " " >> doc/testResults/part_1_and_2/test_1.txt
echo "Done."

echo "Writing test 2 to doc/testResults/part_1_and_2/test_2.txt"
echo "Test 2 (part 1 and 2)" > doc/testResults/part_1_and_2/test_2.txt
echo "Searching BST for: 1 unknown dam name" >> doc/testResults/part_1_and_2/test_2.txt
java -cp bin DamBSTApp "Nothing">> doc/testResults/part_1_and_2/test_2.txt
echo "Done."

echo "Writing test 3 to /doc/testResults/part_1_and_2/test_3.txt"
echo "Test 3 (part 1 and 2)" > doc/testResults/part_1_and_2/test_3.txt
echo "Printing all dam entries from BST" >> doc/testResults/part_1_and_2/test_3.txt
java -cp bin DamBSTApp >> doc/testResults/part_1_and_2/test_3.txt
echo "Done."
