#!/usr/bin/env bash
# wait-for-it.sh: Wait for a service to be available.
# Usage: ./wait-for-it.sh <host>:<port> -- <command>

TIMEOUT=${TIMEOUT:-60}
INTERVAL=${INTERVAL:-5}

if [ $# -lt 1 ]; then
  echo "Usage: $0 host:port -- command args..."
  exit 2
fi

# Find the "--" separator index (if present)
SEP_INDEX=0
for i in $(seq 1 $#); do
  arg=$(eval "echo \$$i")
  if [ "$arg" = "--" ]; then
    SEP_INDEX=$i
    break
  fi
done

if [ "$SEP_INDEX" -eq 0 ]; then
  echo "Missing '--' separator. Usage: $0 host:port -- command args..."
  exit 2
fi

HOSTPORT=$(eval "echo \$1")
HOST=${HOSTPORT%%:*}
PORT=${HOSTPORT#*:}

if [ -z "$HOST" ] || [ -z "$PORT" ] || [ "$HOST" = "$PORT" ]; then
  echo "Invalid host:port: '$HOSTPORT'"
  exit 2
fi

# Build command to run (arguments after --)
CMD=()
for i in $(seq $((SEP_INDEX + 1)) $#); do
  CMD+=("$(eval "echo \$$i")")
done

echo "Waiting for $HOST:$PORT (timeout ${TIMEOUT}s)..."

start_ts=$(date +%s)
while true; do
  if nc -z "$HOST" "$PORT" >/dev/null 2>&1; then
    echo "$HOST:$PORT is available"
    exec "${CMD[@]}"
  fi

  now_ts=$(date +%s)
  elapsed=$((now_ts - start_ts))
  if [ "$elapsed" -ge "$TIMEOUT" ]; then
    echo "Timeout after ${TIMEOUT}s waiting for $HOST:$PORT"
    exit 1
  fi

  echo "Still waiting for $HOST:$PORT... (elapsed ${elapsed}s)"
  sleep "$INTERVAL"
done
