<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pengelolaan Pesan - Tulis Pesan</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="<?php echo base_url();?>assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="<?php echo base_url();?>assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="<?php echo base_url();?>assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /> -->
   <link href="<?php echo base_url();?>assets/css/fonts.css" rel="stylesheet" />
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">UAJY - BPBD</a> 
            </div>
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> Selamat Datang <?php echo $username; ?> <a href="<?php echo site_url('login_control/logout');?>" class="btn btn-danger square-btn-adjust">Keluar</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="<?php echo base_url('assets/img/find_user.png');?>" class="user-image img-responsive"/>
                    </li>

                    <!-- <li>
                        <a href="<?php echo site_url('home_control');?>"><i class="fa fa-home fa-3x"></i> Halaman Utama</a>
                    </li> -->
                    <!-- <li>
                        <a href="<?php echo site_url('petugas_control');?>"><i class="fa fa-users fa-3x"></i> Pengelolaan Petugas<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a  href="<?php echo site_url('petugas_control');?>">Tampil Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_tambah_petugas');?>">Tambah Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_ubah_petugas');?>">Ubah Data</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('petugas_control/view_hapus_petugas');?>">Hapus Data</a>
                            </li>
                        </ul>
                    </li> -->   
                    <li>
                        <a class="active-menu" href="#"><i class="fa fa-envelope-o fa-3x"></i> Pengelolaan Pesan<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="<?php echo site_url('pesan_control');?>">Pesan Masuk</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_pesan_terkirim');?>">Pesan Terkirim</a>
                            </li>
                            <li>
                                <a class="active-sub-menu" href="<?php echo site_url('pesan_control/view_tulis_pesan');?>">Tulis Pesan</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_broadcast_bencana');?>">Broadcast Informasi Bencana</a>
                            </li>
                            <li>
                                <a href="<?php echo site_url('pesan_control/view_broadcast_posko');?>">Broadcast Informasi Posko</a>
                            </li>
                        </ul>
                    </li>  
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Pengelolaan Pesan - Tulis Pesan</h2>   
                        <!-- <h5>Welcome Jhon Deo , Love to see you back. </h5> -->
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
           <div class="row">
                <div class="col-md-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Form Tulis Pesan
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3>Tulis Pesan</h3>
                                    <!--<form role="form">-->
                                     <?php echo form_open("pesan_control/kirim_pesan"); ?> 

                                    <label for="nomor_telepon">Nomor Telepon: </label>
                                    <label for="nomor_telepon" style="color:red;"><?php echo form_error('nomor_telepon', '* <span class="error">', '</span>'); ?></label>
                                    <div class="form-group input-group">
                                        <span class="input-group-addon">+62</span>
                                        <input type="text" class="form-control" id="nomor_telepon" name="nomor_telepon" placeholder="Nomor Telepon">
                                    </div>

                                    <label>atau</label>
                                    <br>
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">Pilih Kontak Petugas</button>
                                    
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="myModalLabel">Pilih Petugas</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                             <!-- Advanced Tables -->
                                                            Kontak Petugas
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="table-responsive" id="c_b">
                                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>Pilih</th>
                                                                            <th>ID Petugas</th>
                                                                            <th>Nama Petugas</th>
                                                                            <th>Username</th>
                                                                            <th>Nomor Telepon</th>
                                                                            <th>Role</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        
                                                                        <?php
                                                                        $i = 0;
                                                                        if(!empty($daftar_petugas))
                                                                        {
                                                                            foreach ($daftar_petugas as $rows) { 
                                                                                $i++;
                                                                            ?>
                                                                            <tr>
                                                                                <td><input type="checkbox" value=<?php echo $rows->no_hp; ?> name="petugas[]"></td>
                                                                                <td><?php echo $rows->id_user; ?></td>
                                                                                <td><?php echo $rows->nama_lengkap; ?></td>
                                                                                <td><?php echo $rows->username; ?></td>
                                                                                <td><?php echo $rows->no_hp; ?></td>
                                                                                <td><?php if($rows->id_role == 1){
                                                                                    echo "Petugas";
                                                                                }
                                                                                else if($rows->id_role == 2)
                                                                                {
                                                                                    echo "Relawan";
                                                                                }
                                                                                ?></td>
                                                                            </tr>   
                                                                            <?php
                                                                            }
                                                                        }
                                                                        ?>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Selesai</button>
                                                    <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <br>
                                    <br>
                                    <div class="form-group">
                                        <fieldset disabled="disabled">
                                            <label for="petugas_checked">Nomor Petugas Dipilih:</label>
                                            <textarea class="form-control" name="petugas_checked" rows="3" placeholder="Nomor Petugas Dipilih" id="petugas_checked" ></textarea>
                                        </fieldset>                                       
                                    </div>
                                    <hr style="height:1px;border:none;color:#333;background-color:#333;" >

                                    <div class="form-group">
                                        <label for="isi_pesan">Isi Pesan</label>
                                        <label for="isi_pesan" style="color:red;"><?php echo form_error('isi_pesan', '* <span class="error">', '</span>'); ?></label>
                                        <textarea class="form-control" name="isi_pesan" rows="3" placeholder="Isi Pesan" id="isi_pesan" ></textarea>
                                        <h5>
                                            <span id="remaining">160 karakter tersisa</span>
                                            <span id="messages">- 1 pesan</span>
                                        </h5>
                                    </div>
                                    
                                    <input type="reset" class="btn btn-primary" value="Reset">
                                    <input type="submit" class="btn btn-default" name="kirim" value="Kirim Pesan" onclick="return checkSendMessage()"/>

                                    <?php echo form_close(); ?>
                                    <!--</form> -->                                
                                </div> 
                            </div>
                        </div>
                     <!-- End Form Elements -->
                    </div>
                </div>
            </div>
            </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
</div>
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/jquery.metisMenu.js"></script>
      <!-- CUSTOM SCRIPTS -->
    <script src="<?php echo base_url();?>assets/js/custom.js"></script>
    <script language="JavaScript" type="text/javascript">
        function checkSendMessage(){
            return confirm('Anda yakin kirim pesan ini?');
        }
    </script>
    <script src="<?php echo base_url();?>assets/js/jquery-1.11.2.min.js"></script>
    <script>
    $(document).ready(function(){
        var $remaining = $('#remaining'),
            $messages = $remaining.next();

        $('#isi_pesan').keyup(function(){
            var chars = this.value.length,
                messages = Math.ceil(chars / 160),
                remaining = messages * 160 - (chars % (messages * 160) || messages * 160);

            $remaining.text(remaining + ' karakter tersisa - ');
            $messages.text(messages + ' pesan');
        });
    });
    </script>
    <script>
        function updateTextArea() {         
         var allVals = [];
         $('#c_b :checked').each(function() {
           allVals.push($(this).val());
         });
         $('#petugas_checked').val(allVals);
      }
     $(function() {
       $('#c_b input').click(updateTextArea);
       updateTextArea();
     });
    </script>
   
</body>
</html>
