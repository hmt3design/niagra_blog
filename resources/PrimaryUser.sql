USE blog_db;

CREATE USER blog_user@localhost IDENTIFIED BY 'codeup';
GRANT ALL PRIVILEGES ON blog_db.* TO blog_user@localhost