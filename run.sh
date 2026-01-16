#!/bin/bash

if [ "$1" == "debug" ]; then
  echo "Starting in DEBUG mode (Port 5005 open)..."
  docker-compose -f docker-compose.yaml -f docker-compose.debug.yml up --build
else
  echo "Starting in NORMAL mode..."
  docker-compose up --build
fi