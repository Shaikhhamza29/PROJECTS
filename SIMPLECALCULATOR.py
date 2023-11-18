import tkinter
from tkinter import *
 
 
root = Tk()
root.title("SIMPLE CALCULATOR")
root.geometry("570x600+100+200")
root.resizable(False,False)
root.configure(bg="#17161b")


label_result = Label(root,width=25,height=2,text="",font=("arial",30))
label_result.pack()

Button(root,text="C",width=5,height=1,bd=1,fg="#fff",bg="#3697f5",font=("arial",30,"bold")).place(x=10,y=100)
Button(root,text="/",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=150,y=100)
Button(root,text="%",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=290,y=100)
Button(root,text="*",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=430,y=100)

Button(root,text="7",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=10,y=200)
Button(root,text="8",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=150,y=200)
Button(root,text="9",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=290,y=200)
Button(root,text="-",width=5,height=1,bd=1,fg="#fff",bg="#2a2d36",font=("arial",30,"bold")).place(x=430,y=200)
root.mainloop()