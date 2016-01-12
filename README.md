# SmartSharedPreferences
SmartSharedPreferences可以让你创建实体类一样创建一个SharedPreferences对象，使用链式方法操作数据。

如果你使用RxJava，这里是RxJava版本的[RxSmartSharedPreferences](https://github.com/YoKeyword/RxSmartSharedPreferences)
# 特性
1、可能是使用最简单SharedPreferences的操作库，只需一个@Spf注解，基于编译时注解

2、链式操作

#使用方法
#### 1、引用
project的gradle.build里添加：
``` groovy
buildscript {
   dependencies {
       classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8' 
   }
}
```
app的gradle.build里添加：
``` groovy
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    compile 'me.yokeyword.smartsharedpreferences:api:1.0.0'
    apt 'me.yokeyword.smartsharedpreferences:compiler:1.0.0'
}

```
#### 2、定义SharedPreferences对象
``` java
@Spf
public class User {
    long token;

    String name;

    String mobile;

    Boolean first;
}
```
#### 3、编译项目，生成Spf_开头的对应文件（如User对应Spf_User）

#### 4、实例化使用
``` java
 Spf_User mSpfUser = Spf_User.create(this);

  // 单数据 edit
  mSpfUser.name().put("name");
  String name = mSpfUser.name().get();
  String mobile = mSpfUser.name().get("defaultValue");

  // 清理Preferences
  mSpfUser.clear();

  // name 是否存在
  boolean exists = mSpfUser.name().exists();

  // 多数据 edit
  mSpfUser.edit()
          .id()
          .put(124)
          .name()
          .put("name")
          .mobile()
          .remove()
          .apply();
// 也可以使用commit()提交，返回boolean类型
```

注：
关于除String/int/boolean/long/float类型之外的属性，可以使用Gson转换成Json(String类型)存入，取出时再通过Gson转成对应对象。

get()方法的默认值：

| 返回类型     | 默认值|
| ------------ | ----- |
| int          | 0     |
| long         | 0l    |
| float        | 0f    |
| boolean      | false |
| String       | ""    |

# 致谢
[AndroidAnnotations](https://github.com/excilys/androidannotations)

# License
``` text
Copyright 2015 YoKeyword

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
