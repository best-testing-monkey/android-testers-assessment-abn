# Intro

I'll be doing these kind of assessment more than once, so documenting the setup progress will be
useful as reference and also to asses in detail **how** I work.

The instructions are broad: "The purpose of this test assignment is to assess the applicant's
automation skills, allowing him/her to show the best they can do and how fast they can learn." and "
Lets see how far you can get in 6-10 hours."

My main tasks will be:

* Get development environment up and running
* Get the supplied app working
* Run the supplied default test
    * ``ExampleInstrumentedTest.kt``
    * ``ExampleUnitTest.kt``
* Exploratory testing on the supplied application
* Think up some test scripts

# Setup

I use a sandbox environment for my professional projects to minimize external influences.

## System

* Xubuntu 24 (Ubuntu with XFCE window manager)
* Android Studio "Koala"

  ```sudo snap install android-studio --classic```

# Work log

| Description / commit message)                              | Additional comments                                                                                                                                                                                                     |
|------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Commit: package detekt RC6-3 unavailable. Changed to RC7-3 | I'm just guessing this new RC is compatible with RC6-3                                                                                                                                                                  |
| Commit: added package junit                                | No junit tests without junit                                                                                                                                                                                            |
| Its a hello world!                                         | This _can't_ be all there is.                                                                                                                                                                                           |
| Determined: no other app code                              | This is actually all there is.                                                                                                                                                                                          |
| Write test plan                                            | [test_plan.md](test_plan.md) may be a bit ambitious, but I'll see how far I'll be able to get in 6 hours                                                                                                                |
| Run existing Test code                                     | This is easier said than done                                                                                                                                                                                           |
| Fresh start                                                | I can't get the app to build on my environment, and I can't spend too much time on this if i'll keep within 10 hours. I'll be building the same application (layout and code) with a recent Gradle that i know to work. |
| Test implementation                                        | Done and referred in [test_plan.md](test_plan.md)                                                                                                                                                                       |
| Cleanup                                                    | removed some duplication and cleaned everything up.                                                                                                                                                                     |