import os
import shutil
def condition(path):
    print("1.pwd - location \n"
          "2.ls - list the files in directory \n"
          "3.mkdir - make a file \n"
          "4.rmdir - remove a file \n"
          "5.rm - remove a document \n"
          "6.cat - read the content in the documents \n"
          "7.write - write the content in the document \n"
          "8.cp - copy the document in one file to another \n"
          "9.mv - move the document from one file to another \n")
    o = input("enter the command ")
    if o =='pwd':
        print(path)
        print("\n THE PATH PRINTED SUCESSFULLY ")
    elif o == 'ls':
        for i in os.listdir(path):
            print(i)
    elif o =='mkdir':
        file = input('ENTER THE FILE NAME ')
        os.mkdir(file)
        print('\nFILE CREATED SUCCESSFULLY ')
    elif o =='rmdir':
        file = input('ENTER THE FILE NAME ')
        os.rmdir(file)
        print('\nFILE DELETED SUCCESSFULLY ')
    elif o =='rm':
        file = input('ENTER \ BEFORE THE FILE NAME WITH ITS EXTENTION ')
        os.remove(path+file)
        print('\nDOCUMENT DELETED SUCCESSFULLY ')
    elif o =='cat':
        file = input('ENTER THE FILE NAME WITH ITS EXTENTION ')
        f = open(file,'r')
        print(f.read())
        f.close()
        print('\nDOCUMENT READED SUCCESSFULLY ')
    elif o =='write':
        file = input('ENTER THE FILE NAME WITH ITS EXTENTION ')
        f = open(file,'w')
        write = input("ENTER THE LINES TO WRITE IN FILES ")
        f.writelines(write)
        f.close()
        print('\nDOCUMENT READED SUCCESSFULLY ')
    elif o == 'cp':
        i = input('ENTER \ AND THE FILE TO BE COPIED ')
        o = input('EBTER \ AND THE DESTINAION ')
        print(path+i)
        shutil.copy(path + i,path + o)
        print('\nTHE FILE COPIED SUCCESSFULLY ')
    elif o == 'mv':
        i = input('ENTER \ AND THE FILE TO BE COPIED ')
        o = input('EBTER \ AND THE DESTINAION ')
        print(path+i)
        shutil.move(path + i,path + o)
        print('\nTHE FILE COPIED SUCCESSFULLY ')
    else:
        print('THE COMMAND DOESNT EXIST ')
path = os.getcwd()
condition(path)
