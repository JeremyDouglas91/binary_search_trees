#!/bin/bash  
echo "Running 3 tests for part 1 and 2..."

echo "Writing test 1 to doc/testResults/part_3_and_4/test_1.txt"
echo "Test 1 (part 3 and 4)" > doc/testResults/part_3_and_4/test_1.txt
echo "Searching AVL Tree for: 3 known dam names" >> doc/testResults/part_3_and_4/test_1.txt

java -cp bin DamAVLapp "Lindleyspoort Dam" >> doc/testResults/part_3_and_4/test_1.txt
echo " " >> doc/testResults/part_3_and_4/test_1.txt

java -cp bin DamAVLapp "Koppies Dam" >> doc/testResults/part_3_and_4/test_1.txt
echo " " >> doc/testResults/part_3_and_4/test_1.txt

java -cp bin DamAVLapp "Woodstock Dam" >> doc/testResults/part_3_and_4/test_1.txt
echo " " >> doc/testResults/part_3_and_4/test_1.txt
echo "Done."

echo "Writing test 2 to doc/testResults/part_3_and_4/test_2.txt"
echo "Test 2 (part 3 and 4)" > doc/testResults/part_3_and_4/test_2.txt
echo "Searching AVL Tree for: 1 unknown dam name" >> doc/testResults/part_3_and_4/test_2.txt

java -cp bin DamAVLapp "Nothing" >> doc/testResults/part_3_and_4/test_2.txt
echo "Done."

echo "Writing test 3 to doc/testResults/part_3_and_4/test_3.txt"
echo "Test 3 (part 3 and 4)" > doc/testResults/part_3_and_4/test_3.txt
echo "Printing all dam entries from AVL Tree" >> doc/testResults/part_3_and_4/test_3.txt

java -cp bin DamAVLapp >> doc/testResults/part_3_and_4/test_3.txt
echo "Done."
