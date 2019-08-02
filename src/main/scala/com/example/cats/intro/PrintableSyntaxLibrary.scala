package com.example.cats.intro

import com.example.cats.intro.PrintableObjectLibrary.Printable

object PrintableSyntaxLibrary {

  implicit class PrintableOps[A](value: A){
   def format(implicit p:Printable[A]) = p.format(value)
   def print(implicit p:Printable[A]) = p.print(value)
  }
}
