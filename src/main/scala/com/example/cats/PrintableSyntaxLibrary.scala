package com.example.cats
import PrintableObjectLibrary.Printable

object PrintableSyntaxLibrary {

  implicit class PrintableOps[A](value: A){
   def format(implicit p:Printable[A]) = p.format(value)
   def print(implicit p:Printable[A]) = p.print(value)
  }
}
