```
  public java.lang.String concat00();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: new           #9                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
         7: ldc           #11                 // String
         9: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        12: aload_0
        13: getfield      #6                  // Field another0:I
        16: invokevirtual #13                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        19: invokevirtual #14                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        22: areturn
      LineNumberTable:
        line 31: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      23     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```

```
  public java.lang.String concat01();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: ldc           #16                 // String 32768
         2: areturn
      LineNumberTable:
        line 36: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       3     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```

```
  public java.lang.String concat02();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: new           #9                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
         7: ldc           #11                 // String
         9: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        12: getstatic     #17                 // Field another2:I
        15: invokevirtual #13                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        18: invokevirtual #14                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        21: areturn
      LineNumberTable:
        line 41: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      22     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```

```
  public java.lang.String concat03();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: ldc           #18                 // String 1073741824
         2: areturn
      LineNumberTable:
        line 46: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       3     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()

```


```
  public java.lang.String concat11();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: new           #9                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
         7: ldc           #11                 // String
         9: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        12: aload_0
        13: getfield      #5                  // Field number:I
        16: invokevirtual #13                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        19: invokevirtual #14                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        22: areturn
      LineNumberTable:
        line 51: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      23     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```

```
  public java.lang.String concat12();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: getfield      #5                  // Field number:I
         4: invokestatic  #19                 // Method java/lang/Integer.toString:(I)Ljava/lang/String;
         7: areturn
      LineNumberTable:
        line 56: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       8     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```

```
  public java.lang.String concat13();
    descriptor: ()Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: new           #9                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
         7: aload_0
         8: getfield      #5                  // Field number:I
        11: invokevirtual #13                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        14: invokevirtual #14                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        17: areturn
      LineNumberTable:
        line 61: 0
        line 62: 11
        line 63: 14
        line 61: 17
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      18     0  this   Lorg/jvmbenchmark/measure4/StringConcat;
    RuntimeVisibleAnnotations:
      0: #55()
```
