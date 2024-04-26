@main def hello(): Unit =
  println("Hello world!")
  println(msg)
  println(s"Running on $arch")

def msg = "I was compiled by Scala 3. :)"

def arch: String =
  import scala.scalanative.unsafe._
  Zone: // We need Zone to allocate memory
    import scalanative.posix.sys.utsname
    import scalanative.posix.sys.utsnameOps.given
    val ptr = stackalloc[utsname.utsname]()
    scala.scalanative.posix.sys.utsname.uname(ptr)
    fromCString(ptr.machine.at(0))
