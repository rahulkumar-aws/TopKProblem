package io.topkproblem.jobs

import com.clearspring.analytics.stream.{Counter, StreamSummary}
import com.twitter.algebird.{Approximate, SpaceSaver, SpaceSaverSemigroup}

import scala.collection.JavaConverters._
import scala.collection.immutable.HashMap
import scala.collection.mutable

object TopKData {

  /**
    *
    * @param it  scala.collection.Iterator[A]
    * @param K   Top K element
    * @return
    */

  def hashmapImp(it: Iterator[String], K: Int): HashMap[String, Int] =
    (it foldLeft (HashMap[String, Int]()))(
      (topK : HashMap[String, Int], input: String) => {
        if (topK contains input) {
          topK updated (input, topK(input) + 1)
        }
        else {
          topK updated (input, 1)
        }
   })

  /**
    *
    * @param it  scala.collection.Iterator[A]
    * @param K   Top K element
    * @return
    */

  def mySpaceSaverImp(it: Iterator[String], K: Int): HashMap[String, Int] =
    (it foldLeft (HashMap[String, Int]()))(
      (topK : HashMap[String, Int], input: String) => {
        if (topK contains input) {
          topK updated (input, topK(input) + 1)
        } else if(topK.size == K){
            val min = topK.minBy(_._2)
            (topK - min._1).updated(input, min._2+1)
        }
        else {
          topK updated (input, 1)
        }
      })

  /**
    *
    * @param it  scala.collection.Iterator[A]
    * @param K   Top K element
    * @return
    */

  def streamLibImp(it: Iterator[String], K: Int): mutable.Buffer[Counter[String]]  ={
    val topk = new StreamSummary[String](100) //larger capacities improve accuracy
    it.foreach(d=>{
      topk.offer(d)
    })
    val counters: java.util.List[Counter[String]] = topk.topK(K)
    counters.asScala
  }

  /**
    *
    * @param it  scala.collection.Iterator[A]
    * @param K   Top K element
    * @return
    */

  def algebirdImp(it: Iterator[String], K: Int): Seq[(String, Approximate[Long], Boolean)]  = {
    val spacesaversemigroup = new SpaceSaverSemigroup[String]
    val topK = it.map(SpaceSaver(100, _))
      .reduce(spacesaversemigroup.plus)
      .topK(10)
    topK
  }
}
