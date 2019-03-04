package com.rimi

object DoNothingAction extends UndoableAction("Do nothing") {
  override def redo(): Unit = {

  }

  override def undo(): Unit = {

  }
}
