#!/usr/bin/env bash

# stop pipeline if QUALITY GATE is FAIL

if [[ ${QUALITY_GATE} = "NOK" ]]
then
    echo "QUALITY GATE FAILED. CHECK SONAR SCANNER RESULT!"
    export QUALITY_GATE="OK"
    exit -1
else
    echo "QUALITY GATE PASSED. SONAR SCANNER'S SUCCESSFUL!"
fi