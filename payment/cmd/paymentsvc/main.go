package main

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"github.com/ServiceComb/go-chassis/core/lager"
	//"code.huawei.com/cse/go-chassis"
	_ "github.com/ServiceComb/go-chassis/server/highway"
	_ "github.com/ServiceComb/go-chassis/server/restful"
	_ "github.com/ServiceComb/go-chassis/transport/tcp"
	"github.com/ServiceComb/go-chassis/examples/payment"
	"github.com/emicklei/go-restful/log"
	"github.com/ServiceComb/go-chassis"
)

const (
	ServiceName = "payment"
)

func main() {
	var (
		declineAmount = 1000
	)
	chassis.Init()
	// Mechanical stuff.
	errc := make(chan error)
	var logger = log.Logger
	var service payment.Service
	{
		service = payment.NewAuthorisationService(float32(declineAmount))
		service = payment.LoggingMiddleware(logger,service)

	}
	 chassis.RegisterSchema("rest", service)
	chassis.Run()

	// Capture interrupts.
	go func() {
		c := make(chan os.Signal)
		signal.Notify(c, syscall.SIGINT, syscall.SIGTERM)
		errc <- fmt.Errorf("%s", <-c)
	}()
	lager.Logger.Error("exit",<-errc)
}
