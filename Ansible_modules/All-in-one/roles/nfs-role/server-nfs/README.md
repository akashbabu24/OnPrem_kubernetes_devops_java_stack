[![Build Status](https://travis-ci.org/CSCfi/ansible-role-nfs.svg)](https://travis-ci.org/CSCfi/ansible-role-nfs)
# Ansible Role: NFS

Installs NFS utilities on RedHat/CentOS or Debian/Ubuntu.

## Requirements

None.

## Role Variables

Available variables are listed below, along with default values (see `defaults/main.yml`):

    nfs_exports: []

A list of exports which will be placed in the `/etc/exports` file. See Ubuntu's simple [Network File System (NFS)](https://help.ubuntu.com/14.04/serverguide/network-file-system.html) guide for more info and examples. (Simple example: `nfs_exports: { "/home/public    *(rw,sync,no_root_squash)" }`).

## Dependencies

None.

## Example Playbook

    - hosts: db-servers
      roles:
        - { role: ansible-role-nfs }

## License

MIT / BSD

## Author Information

This role is based on the one with the same name from [Jeff Geerling](http://jeffgeerling.com/), author of [Ansible for DevOps](http://ansiblefordevops.com/).
Minor changes have been made to deal with CentOS 7. 


