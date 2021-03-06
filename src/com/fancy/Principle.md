#设计模式六大原则
其实原则只要尽量满足就可以,全部原则都满足并不是最好的,这可能让系统变得更加难以维护
凡事都应该以自身需求来定夺,没有太多的规则可寻

##单一职责原则
遵循单一职责原则的优点有：
-   可以降低类的复杂度，一个类只负责一项职责，其逻辑肯定要比负责多项职责简单的多；
-   提高类的可读性，提高系统的可维护性；
-   变更引起的风险降低，变更是必然的，如果单一职责原则遵守的好，当修改一个功能时，可以显著降低对其他功能的影响。

有时候,单一职责原则中,职责这个是一个相对的概念
没有绝对的单一职责的说法,也不必太过刻意去遵守单一职责原则

##里氏替换原则
里氏替换原则包含4层含义：
子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法。
子类中可以增加自己特有的方法。
当子类的方法重载父类的方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松。
当子类的方法实现父类的抽象方法时，方法的后置条件（即方法的返回值）要比父类更严格。

如果子类某个方法与父类相冲突的时候,
应该将原来的父类和子类都继承一个更通俗的基类，原有的继承关系去掉，采用依赖、聚合，组合等关系代替。
反正尽量不要覆盖掉父类的方法,除非你的实现跟父类的原意是一样的,并且更合适,不然出错的几率会很大

##依赖倒置原则
高层模块应该依赖低层模块的抽象,抽象不应该依赖细节,细节应该依赖抽象
高层模块是业务逻辑较为复杂的
低层模块是业务逻辑相对简单的
高层模块需要依赖多个低层模块进行开发时,最好是将低层模块抽象化为接口
也就是面向接口/抽象类 编程

在实际编程中，我们一般需要做到如下3点：
1.  低层模块尽量都要有抽象类或接口，或者两者都有。
2.  变量的声明类型尽量是抽象类或接口。
3.  使用继承时遵循里氏替换原则。

##接口隔离原则
客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。

如果类A只需要用到接口I的一部分方法,而类B也只需要用到接口I的一部分方法
这个时候建议把接口I拆分成三个子接口I1和I2,I3,此时I3为I1和I2的共同部分,I1为类A需要的方法,I2为类B需要的方法

采用接口隔离原则对接口进行约束时，要注意以下几点：
-   接口尽量小，但是要有限度。对接口进行细化可以提高程序设计灵活性是不挣的事实，但是如果过小，则会造成接口数量过多，使设计复杂化。所以一定要适度。
-   为依赖接口的类定制服务，只暴露给调用的类它需要的方法，它不需要的方法则隐藏起来。只有专注地为一个模块提供定制服务，才能建立最小的依赖关系。
-   提高内聚，减少对外交互。使接口用最少的方法去完成最多的事情。

运用接口隔离原则，一定要适度，接口设计的过大或过小都不好。设计接口的时候，只有多花些时间去思考和筹划，才能准确地实践这一原则。

##最少知道原则
一个对象应该对其他对象保持最少的了解。

比如对象A负责管理实例集合ASet
对象B负责管理实例集合BSet
如果B需要依赖到ASet
建议B直接依赖A,再间接依赖ASet而不是直接依赖ASet;

迪米特法则的初衷是降低类之间的耦合，由于每个类都减少了不必要的依赖，因此的确可以降低耦合关系。
但是凡事都有度，虽然可以避免与非直接的类通信，但是要通信，必然会通过一个“中介”来发生联系.
例如本例中，总公司就是通过分公司这个“中介”来与分公司的员工发生联系的。过分的使用迪米特原则，会产生大量这样的中介和传递类，导致系统复杂度变大。
所以在采用迪米特法则时要反复权衡，既做到结构清晰，又要高内聚低耦合。

##开闭原则
对拓展开放,对修改关闭










