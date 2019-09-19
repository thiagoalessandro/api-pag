#!/bin/bash
set -e
echo "local replication postgres trust" >> /var/lib/postgresql/data/pg_hba.conf &&
echo "host replication postgres 127.0.0.1/32 trust" >> /var/lib/postgresql/data/pg_hba.conf &&
echo "host replication postgres ::1/128 trust" >> /var/lib/postgresql/data/pg_hba.conf &&
