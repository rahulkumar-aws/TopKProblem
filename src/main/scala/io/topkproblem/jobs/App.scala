package io.topkproblem.jobs

import com.clearspring.analytics.stream.Counter

import scala.io.{Codec, Source}
import scala.util.Try
import scala.collection.JavaConverters._
import com.google.common.base.Stopwatch
object App {

  def main(args: Array[String]): Unit = {

    val mb = 1024 * 1024
    val runtime = Runtime.getRuntime()
    if (args.length == 0) {
      println("Please Enter number of lookup item [K] & no. of input line [N] [Optional].")
      return
    }

    // Function type
    val algoType = args(0)
    // number of most frequent item
    val K = args(1).toInt
    // number of lines
    val N = args.lift(2)

    println("number of most frequent numbers that we are looking for ", K)
    println("number of lines to read from stdin (optional) ", N)
    println("\n")

    val stream = getStream(N)
    val stopwatch = Stopwatch.createStarted

    algoType match {
      case "basic" => {
        Try(
          TopKData.hashmapImp(stream, K).toSeq.sortWith(_._2 > _._2).take(K).foreach { case (element, rank) => println(s"$element : $rank") }
        )
      }
      case "spacesaver" => {
        Try(
          TopKData.mySpaceSaverImp(stream, K).foreach { case (element, rank) => println(s"$element : $rank") }
        )
      }
      case "stream-lib" => {
        Try(
          TopKData.streamLibImp(stream, K).foreach { case (element) => println(s"${element.getItem} : ${element.getCount}") }
        )
      }
      case "algebird" => {
        Try(
          TopKData.algebirdImp(stream, K).foreach { case (element, approx, _) => println(s"$element : ${approx.max}") }
        )
      }
      case _ => println("No Param provided")
    }

    println("\n")
    println("Used Memory : " + (runtime.totalMemory() - runtime.freeMemory()) / mb + " MB")
    println("Elapsed time: " + stopwatch)

  }

  private def getStream(N: Option[String]): Iterator[String] = {
    implicit val codec = Codec("UTF-8")
    // using Source in order to not read the whole input into memory
    val base = Source.fromInputStream(System.in).getLines
    // limiting input with N lines if N is provided
    N match {
      case Some(n) => base.take(n.toInt)
      case None => base
    }
  }

}
