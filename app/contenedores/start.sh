#!/bin/bash

#Getting the latest binary of node_exporter

wget https://github.com/prometheus/node_exporter/releases/download/v1.7.0/node_exporter-1.7.0.linux-amd64.tar.gz
tar xf node_exporter-1.7.0.linux-amd64.tar.gz
sudo mv node_exporter-1.7.0.linux-amd64/node_exporter /usr/local/bin

rm -rf node_exporter-1.7.0.linux-amd64.tar.gz
rm -rf node_exporter-1.7.0.linux-amd64

#creating a daemon to run node_exporter

sudo tee -a /etc/systemd/system/node_exporter.service > /dev/null <<EOT
[Unit]
Description=Node Exporter
Wants=network-online.target
After=network-online.target

[Service]
User=root
ExecStart=/usr/local/bin/node_exporter

[Install]
WantedBy=default.target
EOT

#Loading the new daemon

sudo systemctl daemon-reload
sudo systemctl start node_exporter
sudo systemctl status node_exporter





