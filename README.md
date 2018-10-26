### Find Top K in Stream of Data ###

Current solution using Space Saving algorithm for finding TopK 
element in data stream.

The solution has four functions that using HashMap, [algebird](https://github.com/twitter/algebird) and [stream-lib](https://github.com/addthis/stream-lib) for finding Top K:

```bash
io.topkproblem.jobs.hashmapImp
io.topkproblem.jobs.mySpaceSaverImp
io.topkproblem.jobs.streamLibImp
io.topkproblem.jobs.algebirdImp
```

we can switch functions using args(0) parameter:

*  algebird
*  basic
*  spacesaver
*  stream-lib

**Run**

* args(0) -- Algo type
* args(1) -- K param
* args(2) -- N number of lines to read

```bash
# Sample data set is present in resources folder
sbt "runMain io.topkproblem.jobs.App algebird 10" < /Users/rahulkumar/Downloads/work/data/dataset.csv
```
Solution will also give Memory used and Elapsed time for each function.

**Output**

```text
jjj.ronl-xyrvanamrvtra.qr : 547393
jjj.ronl.qr : 473162
fhpura.zbovyr.qr : 286804
g-bayvar.qr : 264376
jro.qr : 224514
tzk.qr : 193580
ronlnqiregvfvat.pbz : 189793
ert.ronl.qr : 184734
jjj.fcvrtry.qr : 168085
jjj2.ronlnqiregvfvat.pbz : 161781

Used Memory : 339 MB
Elapsed time: 11.12 s

```

**Verify count**

```bash
cat dataset.csv | grep -c '\<jjj.ronl-xyrvanamrvtra.qr\>'
```




