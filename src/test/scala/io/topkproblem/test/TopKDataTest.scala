package io.topkproblem.test

import io.topkproblem.jobs.TopKData
import org.scalatest.{BeforeAndAfter, FunSuite}

class TopKDataTest extends FunSuite with BeforeAndAfter{

  var DataSet: Iterator[String] = _
  val topK = 10

  before {
    DataSet = Iterator(
      '1' to '9',
      '1' to '9'
    ).flatten.map(_.toString)
  }

  test("resulting top K list contains K elements") {
    val result = TopKData.algebirdImp(DataSet, topK)
    assert(result.size == topK)
  }

}
