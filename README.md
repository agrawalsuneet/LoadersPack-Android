# LoadersPack
> Android Loaders            [![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=59f8e6a0458f2b00011be986&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/59f8e6a0458f2b00011be986/build/latest?branch=master)

> A replacement of default android material progressbar with loaders


### ClockLoader
![clockloader](https://user-images.githubusercontent.com/12999622/32249174-f36a9eb8-be7f-11e7-98aa-48d398b178f8.gif)

### [RippleLoader](https://www.youtube.com/watch?v=FkBRhS4nJ0o)
![RippleLoader](https://user-images.githubusercontent.com/12999622/33100022-cffe8e62-cf0a-11e7-86bf-473c732085c3.gif)

Other loaders: [LinearDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [CircularDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [LazyLoader](https://github.com/agrawalsuneet/DotsLoader), [TashieLoader](https://github.com/agrawalsuneet/DotsLoader), [FourFoldLoader](https://github.com/agrawalsuneet/FourFoldLoader), [ZipZapLoader](https://github.com/agrawalsuneet/FourFoldLoader)

## How To use
include below dependency in build.gradle of application and compile it
```
compile 'com.agrawalsuneet.androidlibs:loaderspack:0.1'
```

### ClockLoader
##### Through XML
```
<com.agrawalsuneet.loaderspack.loaders.ClockLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:clock_animSpeedMultiplier="0.5"
        app:clock_bigCircleColor="@color/colorPrimary"
        app:clock_bigCircleRadius="80dp"
        app:clock_hourHandColor="@color/colorAccent"
        app:clock_hourHandLength="40dp"
        app:clock_innerCircleColor="@color/colorPrimaryDark"
        app:clock_innerCircleRadius="5dp"
        app:clock_minuteHandColor="@color/colorAccent"
        app:clock_minuteHandLength="60dp"
        app:clock_outerCircleBorderColor="@color/colorAccent"
        app:clock_outerCircleBorderWidth="10dp" />
```
##### Through Code
* Kotlin
```
        var clock = ClockLoader(this)
                .apply {
                    outerCircleBorderWidth = 8.0f
                    bigCircleRadius = 150.0f
                    minuteHandLength = 120.0f
                    hourHandLength = 80.0f
                    innerCircleRadius = 15.0f

                    outerCircleBorderColor = ContextCompat.getColor(context, R.color.colorAccent)
                    bigCircleColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    innerCircleColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                    hourHandColor = ContextCompat.getColor(context, R.color.colorAccent)
                    minuteHandColor = ContextCompat.getColor(context, R.color.colorAccent)

                    animSpeedMultiplier = 2.0f
                }


        containerLayout.addView(clock)
```

* Java
```
        ClockLoader clockLoader = new ClockLoader(this);
        clockLoader.setOuterCircleBorderWidth(8.0f);
        clockLoader.setBigCircleRadius(150.0f);
        clockLoader.setMinuteHandLength(120.0f);
        clockLoader.setHourHandLength(80.0f);
        clockLoader.setInnerCircleRadius(15.0f);
        
        clockLoader.setOuterCircleBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setBigCircleColor(ContextCompat.getColor(this, R.color.colorPrimary));
        clockLoader.setInnerCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        clockLoader.setHourHandColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setMinuteHandColor(ContextCompat.getColor(this, R.color.colorAccent));
        
        clockLoader.setAnimSpeedMultiplier(2.0f);
        
        container.addView(clockLoader);
```

### RippleLoader
##### Through XML
```
<com.agrawalsuneet.loaderspack.loaders.RippleLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:ripple_animDuration="500"
        app:ripple_circleColor="@color/blue"
        app:ripple_circleInitialRadius="50dp"
        app:ripple_fromAlpha="1.0"
        app:ripple_interpolator="@android:anim/decelerate_interpolator"
        app:ripple_startLoadingDefault="true"
        app:ripple_toAplha="0.01" />
```
##### Through Code
* Kotlin
```
        val ripple = RippleLoader(baseContext).apply {
            circleInitialRadius = 80
            circleColor = resources.getColor(R.color.black)
            fromAlpha = 1.0f
            toAlpha = 0f
            animationDuration = 1000
            interpolator = DecelerateInterpolator()
        }


        containerLayout.addView(ripple)
```

* Java
```
        RippleLoader ripple = new RippleLoader(this);
        ripple.setCircleInitialRadius(80);
        ripple.setCircleColor(getResources().getColor(R.color.red));
        ripple.setFromAlpha(1.0f);
        ripple.setToAlpha(0.01f);
        ripple.setAnimationDuration(1000);
        ripple.setInterpolator(new DecelerateInterpolator());
        ripple.setStartLoadingDefault(true);
        
        container.addView(ripple);
```

Please take a 2 mins survey to make this library better [here](https://goo.gl/forms/v0SZS0oI9rvInzdB3).
It won't take more than 2 mins I promise :) or feel free to drop an email at agrawalsuneet@gmail.com if face any issue or require any additional functionality in it.
```
Copyright 2017 Suneet Agrawal

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
