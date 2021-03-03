package main

import (
	"fmt"
	"time"
)

func f(name string) {
	for i := 0; i < 100; i++ {
		fmt.Println("协程执行中" + name)
	}
}

func main() {
	go f("协程111111")
	go f("协程222222")
	for i := 0; i < 100; i++ {
		fmt.Println("线程执行中")
	}
	go f("协程333333")
	time.Sleep(time.Second)
}
