#!/bin/bash
for i in {1..212}; 
do 
	echo $i ;
	head -n$i ../data/Dam_Data_Fixed.csv > ../data/subsets/test_$i.csv
done